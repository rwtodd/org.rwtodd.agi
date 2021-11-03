(ns org-rwtodd.agi.resources
  (:use     [org-rwtodd.agi.res-decode])
  (:require [clojure.java.io :as io])
  (:import  [java.util ArrayDeque Arrays]
            [java.io RandomAccessFile Closeable File]))

;; ====== Validation
(defn game-path?
  "Verify that this appears to be an AGI game directory"
  [path]
  (let [dir (io/file path)]
    (and (.isDirectory dir)
         (.exists (io/file dir "AGIDATA.OVL")))))

;; ====== Game Version Info
;; ---- support functions for `extract-version` -------------
(defn- digit? [x] (<= 48 x 57)) ;; 48-57 == ASCII 0-9
(defn- dot?   [x] (== x 46))    ;; 46 == ASCII '.'
(def ^:private long-vernum  [digit? dot? digit? digit? digit? dot? digit? digit? digit?])
(def ^:private short-vernum (subvec long-vernum 0 5))
(defn- vermatch [ver bs]  (every? true? (map #(%1 %2) ver bs)))

(defn extract-version
  "Extract the AGI version data from the provided seq of bytes, BS. Typically
   this data will come from the AGIDATA.OVL file"
  [bs]
  (let [;; get the first tail of the input which matches a short version number
        match (first (filter #(vermatch short-vernum %)
                             (reductions (fn [s _] (rest s)) bs bs)))
        
        ;; attempt to match the longer version, and take 9 chars
        ;; if successful.  Otherwise, take the short version we
        ;; already matched
        strver (apply str
                      (sequence (comp (take (if (vermatch long-vernum match) 9 5))
                                      (map unchecked-char))
                                match))]
    ;; make sure the resulting string is one of the valid lengths
    (case (count strver)
      (5 9)   strver
      (6 7 8) (.substring strver 0 5)
      nil)))

(defn numeric-version
  "Convert an AGI version string to a double. It is expected to be either
  d.ddd or d.ddd.ddd and any other format will cuase an exception."
  [sv]
  (Double/parseDouble (if (== (count sv) 9)
                        (str (.substring sv 0 5) (.substring sv 6))
                        sv)))

(defn load-version
  "Determine the game's AGI version based on the AGIDATA.OVL file."
  [path]
  (-> (read-entire-file path "AGIDATA.OVL") extract-version numeric-version))

(defn determine-v3-prefix
  "Version 3 AGI games have a prefix on their resource files, and we can
  figure out what it is by inspecting the file system.  Needs the PATH to
  the root directory of the game."
  [path]
  (let [fls    (file-seq (io/file path))
        volf   (first (filter #(.endsWith (.getName %) "VOL.0") fls))
        volstr (and volf (.getName volf))
        pfx    (and volf (.substring volstr 0 (- (.length volstr) 5)))]
    (if (.exists (io/file path (str pfx "DIR")))
      pfx
      (throw (ex-info "Can't determine game prefix!" {:dir path})))))

;; ====== Resource Directories
(def ^:private v2-directories
  { :logics "LOGDIR", :sounds "SNDDIR", :views "VIEWDIR", :pics "PICDIR" })

(defrecord ResourceEntry [^byte volume ^int offset])

(defn parse-resdir
  "Parse the given DIR into ResourceEntry's, or `nil` if the location is
  all 0xf's."
  [dir]
  (into []
        (comp (partition-all 3)
              (map (fn [[b1 b2 b3]]
                     (let [vol  (bit-and (bit-shift-right b1 4) 0x0f)
                           offs (bit-or (bit-shift-left (bit-and b1 0x0f) 16)
                                        (bit-shift-left (bit-and b2 0xff) 8)
                                        (bit-and b3 0xff))]
                       (when (or (not= vol 0x0f)
                                 (not= offs 0xfffff))
                         (->ResourceEntry vol offs))))))
        dir))

(defn load-resource-directories
  "Returns a map of :sound, :view, :pic, and :logic resource directories from the
  game's directory in PATH.  How this is done depends no the game version.  For V3
  directories, you must pass the game's PREFIX."
  ([path]
   (zipmap (keys v2-directories)
           (map (comp parse-resdir (partial read-entire-file path))
                (vals v2-directories))))
  ([path prefix]
   (let [dirfile (read-entire-file path (str prefix "DIR"))
         logicO  (read-16-le dirfile 0)
         picO    (read-16-le dirfile 2)
         viewO   (read-16-le dirfile 4)
         soundO  (read-16-le dirfile 6)
         end     (alength dirfile)]
     { :logics (parse-resdir (aslice dirfile logicO picO))
      :pics    (parse-resdir (aslice dirfile picO viewO))
      :views   (parse-resdir (aslice dirfile viewO soundO))
      :sounds  (parse-resdir (aslice dirfile soundO end)) })))

;; ====== Words.Tok File
(defn parse-words-tok
  "Parse the contents of a WORDS.TOK file into a dictionary from words to group
  numbers."
  [^bytes src]
  (let [sb  (StringBuilder. 48)
        end (alength src)]
    (loop [idx   (read-16-be src 0)
           wdict (transient {})]  ; word-to-group
      (if (>= (inc idx) end) ;; have to test idx+1 since there's often a last byte
        (persistent! wdict)
        (do (.setLength sb  (read-8 src idx))
            ;; the word is encoded XOR'ed by 0x7f, and the high-bit is
            ;; set on the last byte of the word.  Then, a 16-bit word
            ;; group number finishes the entry.
            (let [new-idx (loop [idx (inc idx)]
                            (let [ch (aget src idx)]
                              (.append sb (->> ch
                                               (bit-and 0x7f)
                                               (bit-xor 0x7f)
                                               unchecked-char))
                              (if (neg? ch) ; high-bit == last byte
                                (inc idx)   ; return next index to read
                                (recur (inc idx)))))]
              (recur (+ new-idx 2)
                     (assoc! wdict (.toString sb) (read-16-be src new-idx)))))))))

(defn load-words-tok
  "Loads the game's WORDS.TOK file, given the PATH to the game."
  [path]
  (->> "WORDS.TOK" (read-entire-file path) parse-words-tok))

;; ====== Object File
(defrecord AgiObject [name starting-room])

(defn parse-objects
  [version  ^bytes src]
  (when (>= version 2.411) (avis-durgan! src))
  (let [end-idx   (+ 3 (read-16-le src 0))
        max-anim  (read-8 src 2)]
    (loop [idx 3, objs (transient [])]
      (if (>= idx end-idx)
        { :max-anim max-anim, :objects (persistent! objs) }
        (recur (+ idx 3)
               (conj! objs (->AgiObject (read-asciiz src (+ 3 (read-16-le src idx)))
                                        (read-8 src (+ idx 2)))))))))

(defn load-objects
  "Load an AGI game's OBJECTS file, given the game path and the game version."
  [path version]
  (->> "OBJECT" (read-entire-file path) (parse-objects version)))

;; ====== Logic Resources
;; these are basically divided into a byte array and a vector of strings.
(defn parse-logic
  "Separate out the script strings from the bytecodes and return them."
  [version ^bytes src]
  (let [text-area (+ 2 (read-16-le src 0))
        msg-count (read-8 src text-area)
        msg-area  (+ 3 text-area)]
    (when (< version 3.0)
      (avis-durgan! src (+ msg-area (* 2 msg-count)) (alength src)))
    { :byte-codes (Arrays/copyOfRange src 2 text-area)
     :msgs (into []
                 (map (fn [idx]
                        (let [offset (read-16-le src idx)]
                          (when (pos? offset)
                            (read-asciiz src (+ msg-area offset -2))))))
                 (range msg-area (+ msg-area (* 2 msg-count)) 2)) }))

;; ====== Sound Resources
(defrecord SoundTone [^int time ^int duration ^short freq ^byte attenuation])
(defrecord NoiseTone [^int time ^int duration ^short freq ^byte attenuation ^byte ntype])
(defn noise-type
  "turn noise type into a keyword, a utility function"
  [ntype]
  (case ntype
    0  :linear
    1  :white
    nil))

(defn- check-voice-len
  "assert that the length of the voice is ok, and return the proper last index"
  [start end ^bytes src]
  (let [r (mod (- end start) 5)]
    (cond
      (zero? r) end
      
      (and (== 2 r)
           (== -1 (aget src (dec end)) (aget src (- end 2))))
      (- end 2)

      :else (throw (ex-info "Bad length of sound voice!" { :len (- end start) })))))

(defn- parse-voice
  "parse a single voice of a sound resource"
  [^bytes src idx end]
  (let [end (check-voice-len idx end src)]
    (loop [idx idx, curtime 0, result (transient [])]
      (if (>= idx end)
        ;; all done!
        (persistent! result)

        ;; read the next note
        (let [atten (bit-and (aget src (+ idx 4)) 0x0f)
              dur   (read-16-le src idx)
              freq  (bit-or (bit-shift-left (bit-and (aget src (+ idx 2)) 0x3f) 4)
                            (bit-and (aget src (+ idx 3)) 0x0f))]
          (recur (+ idx 5)
                 (+ curtime dur)
                 (if (< atten 15)
                   (conj! result (->SoundTone curtime dur freq atten))
                   result)))))))

(defn- parse-noise
  "parse the noise channel of a sound resource"
  [^bytes src idx end]
  (let [end (check-voice-len idx end src)]
    (loop [idx idx, curtime 0, result (transient [])]
      (if (>= idx end)
        ;; all done!
        (persistent! result)

        ;; read the next note
        (let [ntype (if (zero? (bit-and (aget src (+ idx 3)) 0x04))
                      0  ;; LINEAR
                      1) ;; WHITE NOISE
              atten (bit-and (aget src (+ idx 4)) 0x0f)
              dur   (read-16-le src idx)
              freq  (bit-and
                     (bit-shift-left 0x10 (bit-and (aget src (+ idx 3)) 3))
                     0x70)]
          (recur (+ idx 5)
                 (+ curtime dur)
                 (if (< atten 15)
                   (conj! result (->NoiseTone curtime dur freq atten ntype))
                   result)))))))

(defn parse-sound
  "Transform a sound resource's raw bytes into a representation of the tune."
  [^bytes src]
  (let [v1 (read-16-le src 0), v2 (read-16-le src 2),
        v3 (read-16-le src 4), vn (read-16-le src 6)
        parsed { :voices [(parse-voice src v1 v2)
                          (parse-voice src v2 v3)
                          (parse-voice src v3 vn)]
                :noise (parse-noise src vn (alength src)) }]
    (assoc parsed
           :audible-length (transduce (comp (keep #(peek %))
                                            (map #(+ (:time %) (:duration %))))
                                      max
                                      0
                                      (cons (:noise parsed) (:voices parsed))))))

;; ====== File Cache
;; We don't want to keep opening and closing resource files, so we
;; need a cache.  This will be a sneaky mutable variable, which should
;; only be accessed through the functions in this section.
(deftype FileCacheEntry [file ^RandomAccessFile rafile]
  Closeable
  (close [this] (.close rafile)))

(def ^:private file-cache-lock (Object.))
(def file-cache-size 3)
(def ^:private file-cache "mutable file cache" (ArrayDeque.))
(defn locate-file
  "Look up a random access file in the cache, opening it if necessary.
  The cache lock should be locked when calling this function."
  [^File f]
  (or
   ;; Option 1: get it from the cache
   (some #(and (= (.file %) f) (.rafile %)) file-cache)
   ;; Option 2: put a new entry in the cache
   (let [newf (->FileCacheEntry f (RandomAccessFile. f "r"))]
     (when (>= (.size file-cache) file-cache-size)
       (.. file-cache remove close))
     (.add file-cache newf)
     (.rafile newf))))

(defn clear-cache
  "Clear any file caches in use."
  []
  (locking file-cache-lock
    (dorun (map #(.close %) file-cache))
    (.clear file-cache)))

(defn load-resource-bytes
  "Loads the actual resource bytes from disk"
  [info ^ResourceEntry entry]
  (locking file-cache-lock
    (let [^RandomAccessFile file (locate-file
                                  (io/file (:path info)
                                           (str (:prefix info) "VOL." (.volume entry))))]
      (.seek file (.offset entry))
      (if (< (:version info) 3.0)
        
        ;; Version 1 or 2 Resource
        (let [header (byte-array 5)]
          (.readFully file header)
          (if (and (== (read-16-be header 0) 0x1234)
                   (== (read-8 header 2) (.volume entry)))
            ;; good header, read the data
            (let [res (byte-array (read-16-le header 3))]
              (.readFully file res)
              res)
            ;; header failed validation!
            (throw (ex-info "Bad resource header!" {:entry entry
                                                    :path (:path info)}))))
        
        ;; Version 3 Resource
        (let [header (byte-array 7)]
          (.readFully file header)
          (if (and (== (read-16-be header 0) 0x1234)
                   (== (bit-and 0x7f (aget header 2)) (.volume entry)))
            ;; good header, read the data and decompress if needed
            (let [pic-compressed (neg? (aget header 2))
                  reslen         (read-16-le header 3)
                  ^bytes res     (byte-array (read-16-le header 5))]
              (.readFully file res)
              (cond
                (== reslen (alength res))  res
                pic-compressed             (expand-pic res reslen)
                :else                      (expand-lzw res reslen)))
            ;; header failed validation!
            (throw (ex-info "Bad resource header!" {:entry entry
                                                    :path (:path info)}))))))))
        
;; ====== High-Level Interface

(defn load-game-info
  "This is the main high-level function in this package, which verifies the
  given PATH points to an AGI game, and loads version and resource directory
  information into a map.  This map will be used by the other functions to
  load resources.  By default it loads words and objects, but this can be
  avoided by passing `false` to the ALL? parameter."
  ([path] (load-game-info path true))
  ([path all?]
   (if (game-path? path)
     (let [ver  (load-version path)
           pfx  (when (>= ver 3.0) (determine-v3-prefix path))]
       (merge { :version ver, :path (io/file path) }
              (if pfx
                (load-resource-directories path pfx)
                (load-resource-directories path))
              (when pfx { :prefix pfx })
              (when all? { :words  (load-words-tok path)
                          :objects (load-objects path ver) })))

     (throw (ex-info "Not a game directory!" {:dir path})))))  

(defn load-logic
  "Load logic script number NUM from the game associated with INFO"
  [info num]
  (when-let [entry (nth (:logics info) num nil)]
    (parse-logic (:version info) (load-resource-bytes info entry))))

(defn load-sound
  "Load sound number NUM from the game associated with INFO"
  [info num]
  (when-let [entry (nth (:sounds info) num nil)]
    (parse-sound (load-resource-bytes info entry))))

;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
