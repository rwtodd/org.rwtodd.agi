(ns org-rwtodd.agi.resources
  (:use     [org-rwtodd.agi.res-decode])
  (:require [clojure.java.io :as io]))

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

;; ====== Parse words
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
  (let [src (read-entire-file path "WORDS.TOK")]
    (parse-words-tok src)))

;; ====== High-Level Interface

(defn load-game-info
  "This is the main high-level function in this package, which verifies the
  given PATH points to an AGI game, and loads version and resource directory
  information into a map.  This map will be used by the other functions to
  load resources."
  [path]
  (if (game-path? path)
    (let [ver (load-version path)
          pfx (when (>= ver 3.0) (determine-v3-prefix path))]
      (merge { :version ver, :path (io/file path) }
             (if pfx
               (load-resource-directories path pfx)
               (load-resource-directories path))
             (and pfx { :prefix pfx })))

    (throw (ex-info "Not a game directory!" {:dir path}))))


;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
