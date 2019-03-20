(ns org.rwtodd.agi-game.resources
  (:require [clojure.java.io :as io]
            [org.rwtodd.agi-game.lru :as lru]
            [org.rwtodd.agi-game.sound :as snd]
            [org.rwtodd.agi-game.view :as view]
            [org.rwtodd.agi-game.objects :as objects]
            [org.rwtodd.agi-game.logic :as logic]
            [org.rwtodd.agi-game.words :as words]))

;; track the game specs we've heard about
(def games "Tracks the games that have been registered." (atom {}))

(defn registered-games
  "return all the games registered with `register-game!`"
  []
  (keys @games))

(defn game-spec
  "If GAME is a keyword, look it up in our game list. Return maps as-is, assuming
  it must be a game-spec."
  [game]
  (if (keyword? game)
    (game @games)
    game))

(defn game-file
  "Compose a full path to a game file FNAME for GAME"
  [game fname]
  (io/file (-> game game-spec :dir) fname))

(defn game-volume
  "Generate a java.io.File for vol NUM of GAME."
  [game num]
  (let [gs (game-spec game)]
    (io/file (:dir gs) (format (str (:prefix gs) "VOL.%d") num))))

(defn determine-game-version
  "Open the AGIDATA.OVL file and dig out the version number string."
  [game]
  (with-open [agivol (io/input-stream (game-file game "AGIDATA.OVL"))]
    (loop [so-far []]
      (let [ch (.read agivol)]
        (cond
          ;; we ran out of characters!
          (= -1 ch) (throw (Exception. "Can't determine game version!"))

          ;; dots are allowed in certain positions
          (= ch (int \.))
          (case (count so-far)
            (1 5) (recur (conj so-far ch))
            (recur []))

          ;; digits are allowed in certain positions
          (and (>= ch (int \0)) (<= ch (int \9)))
          (case (count so-far)
            (0 2 3 4 6 7 8) (recur (conj so-far ch)) 
            (recur []))

          ;; anything else... see if we are done or not...
          :else
          (case (count so-far)
            (5 9) (apply str (map char so-far))
            (recur [])))))))

(defn parse-v2-dir
  "Read the AGI-v2 resource dir of the given type (e.g., SNDDIR for sounds)"
  [game type]
  (let [dirf  (case type 
                :sound "SNDDIR"  :pic "PICDIR"
                :logic "LOGDIR"  :view "VIEWDIR"
                (throw (Exception. "Unknown AGI resource type!")))
        buf (byte-array 3)]
    (with-open [dir (io/input-stream (game-file game dirf))]
      (loop [num 0, result (transient [])]
        (case (.readNBytes dir buf 0 3)
          0 (persistent! result)
          3 (recur (inc num)
                   (conj! result
                          (let [vol (bit-shift-right (bit-and (aget buf 0) 0xff) 4)
                                loc (bit-or (bit-shift-left (bit-and (aget buf 0) 0x0f) 16)
                                            (bit-shift-left (bit-and (aget buf 1) 0xff) 8)
                                            (bit-and (aget buf 2) 0xff))]
                            (if (and (= vol 0x0f) (= loc 0xfffff))
                              { :num num :present false }
                              { :num num :volume vol :location loc }))))
          (throw (Exception. "Bad format of DIR file!")))))))
    
(defn determine-v3-prefix
  "Figure out what the prefix string on the resource
files is, in the v3 game at ROOT."
  [root]
  (let [fls (file-seq root)
        volf (first (filter #(.endsWith (.getName %) "VOL.0") fls))
        volstr (and volf (.getName volf))
        pfx (and volf (.substring volstr 0 (- (.length volstr) 5)))]
    (if (.exists (io/file root (str pfx "DIR")))
      pfx
      (throw (Exception. "Can't determine game prefix!")))))
        
(defn parse-v3-dirs
  "Read the combined DIR file for v3 resources."
  [game]
  { :sound nil :logic nil :view nil :pic nil })

(defn read-game-metadata
  "Builds initial metadata about the game (its version)"
  [game]
  (let [gspec   (game-spec game)
        version (determine-game-version gspec)
        vernum  (Double. (if (> (.length version) 5)
                           (.concat (.substring version 0 5)
                                    (.substring version 6))
                           version))
        v2data  { :version version, :vernum vernum }]
    (if (< vernum 3)
      v2data
      (assoc v2data :prefix (determine-v3-prefix (:dir gspec))))))

(defn update-registered-game!
  "Merge the given INFOS maps into an already-registered game at KEY"
  [key & infos]
  (key
   (swap! games update-in [key] (fn [m] (apply merge m infos)))))

(defn register-game!
  "Register a game with a given ROOT-DIR. Keyword KEY will be used to
  refer to the game for the rest of the resource-loading functions."
  [key root-dir]
  (let [gspec { :key key, :dir (io/file root-dir) }]
    (update-registered-game! key gspec (read-game-metadata gspec))))
   
(def resource-lru-cache
  "A LRU cche of the last few resources loaded."
  (atom (lru/make-lru 50)))

(defn load-v2-resource
  "Read a v2 resource from VOLUME at offset LOCATION for GAME"
  [game {:keys [location volume]}]
  (if volume
    (with-open [volfile (java.io.RandomAccessFile. (game-volume game volume) "r")]
      (.seek volfile location)
      (let [header (byte-array 5)]
        (.readFully volfile header)
        (when-not (and (= (aget header 0) 0x12)
                       (= (aget header 1) 0x34)
                       (= (aget header 2) volume))
          (throw (Exception. "Bad VOL data!")))
        (let [res-len (bit-or (bit-shift-left (bit-and (aget header 4) 0xff) 8)
                              (bit-and (aget header 3) 0xff))
              buff (byte-array res-len)]
          (.readFully volfile buff)
          buff)))
    ;; resource not present
    nil))

(defn resource-key
  "Build a LRU-cache key for a resource"
  [game type num]
  (str num type game))

(defn get-resource-dir
  "Verify that GAME has a resource dir for TYPE, or
   load the resource dir from disk and cache it.
  Either way, returns a a resource directory if there is one."
  [game type]
  (let [gspec (game-spec game)
        cached (or (type gspec)
                   (type ((:key gspec) @games)))]
    (if cached
      cached
      (type
        (update-registered-game!
         (:key gspec)
         (if (< (:vernum gspec) 3)
           { type (parse-v2-dir gspec type) }
           (parse-v3-dirs gspec)))))))

(defonce avis-durgan
  (vec (.getBytes "Avis Durgan" java.nio.charset.StandardCharsets/US_ASCII)))

(defn decode-bytes!
  "xor SRC byte array against repeating cycles of KEY"
  ([^bytes src key start-idx]
   (let [len (alength src)]
     (loop [idx start-idx, keycyc (cycle key)]
       (if (< idx len)
         (do
           (aset-byte src idx (bit-xor (aget src idx) (first keycyc)))
           (recur (inc idx) (next keycyc)))
         src))))
  ([src key] (decode-bytes! src key 0)))

(defn read-u16
  "given the low and high byte (as java signed bytes), compose
  an unsigned 16-bit number."
  [low high]
  (bit-or (bit-and low 0xff)
          (bit-shift-left (bit-and high 0xff) 8)))

(defn decrypt-v2-logic!
  "V2 logic files have encrypted text inside, but V3 do not.
  So, in V2's case, decrypt it before parsing."
  [^bytes raw]
  (let [text-area      (+ 2 (read-u16 (aget raw 0) (aget raw 1)))
        message-count  (bit-and (aget raw text-area) 0xff)
        decrypt-start  (+ text-area 3 (* 2 message-count))]
    (decode-bytes! raw avis-durgan decrypt-start)))
  
(defn count-resources
  "Look up the number of resources of type TYPE there are in GAME"
  [game type]
  (count (get-resource-dir game type)))

(defn load-resource
  "Load the resource number NUM from GAME of the given TYPE."
  [game type num]
  (let [gspec (game-spec game)
        key (resource-key (:key gspec) type num)]
    (or (lru/lookup @resource-lru-cache key)
        (let [rdir (get-resource-dir gspec type)
              rspec (and (< num (count rdir)) (rdir num))
              raw   (and rspec (if (< (:vernum gspec) 3)
                                 (load-v2-resource gspec rspec)
                                 ;; todo: handle v3
                                 (load-v2-resource gspec rspec)))
              res   (and raw (case type
                               :sound (snd/parse-sound raw)
                               :logic (logic/parse-logic
                                       (if (< (:vernum gspec) 3)
                                         (decrypt-v2-logic! raw)
                                         raw))
                               :view  (view/parse-view raw)
                               raw))]
          (if res
            (do 
              (swap! resource-lru-cache lru/conj key res)
              res)
            nil)))))

(defn load-objects
  "Load the objects file for a game."
  [game]
  (let [gspec (game-spec game)
        key (str "object" (:key gspec))]
    (or (lru/lookup @resource-lru-cache key)
        (let [obj-decoder (if (>= (:vernum gspec) 2.411)
                            #(decode-bytes! % avis-durgan)
                            identity)
              ofile  (game-file gspec "OBJECT")
              obytes (obj-decoder
                      (with-open [ostream (io/input-stream ofile)]
                        (.readAllBytes ostream)))
              objects (objects/parse-objects obytes)]
          (when objects
            (swap! resource-lru-cache lru/conj key objects)
            objects)))))

(defn load-words
  "Load the words.tok file for a game."
  [game]
  (let [gspec (game-spec game)
        key (str "words" (:key gspec))]
    (or (lru/lookup @resource-lru-cache key)
        (let [wfile  (game-file gspec "WORDS.TOK")
              wbytes (with-open [stream (io/input-stream wfile)]
                       (.readAllBytes stream))
              toks (words/parse-words wbytes)]
          (when toks
            (swap! resource-lru-cache lru/conj key toks)
            toks)))))

 
