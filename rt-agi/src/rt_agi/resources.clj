(ns rt-agi.resources
  (:require [clojure.java.io :as io]
            [rt-agi.lru :as lru]))

;; track the game specs we've heard about
(def games (atom {}))

(defn loaded-games []
  "return all the games registered with `add-game`"
  (keys @games))

(defn game-spec [game]
  "If GAME is a keyword, look it up in our game list. Return maps as-is, assuming
it must be a game-spec."
  (if (keyword? game)
    (game @games)
    game))

(defn game-file [game fname]
  "Compose a full path to a game file FNAME for GAME"
  (io/file (-> game game-spec :dir) fname))

(defn game-volume [game num]
  "Generate a java.io.File for vol NUM of GAME."
  (let [gs (game-spec game)]
    (io/file (:dir gs) (format (:volume-fmt gs) num))))

(defn determine-game-version [root-dir]
  "Open the AGIDATA.OVL file and dig out the version number string."
  (with-open [agivol (io/input-stream (io/file root-dir "AGIDATA.OVL"))]
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

(defn parse-v2-map [f]
  "Read the AGI-v2 resource map in file F (e.g., SNDDIR for sounds)"
  (let [buf (byte-array 3)]
    (with-open [dir (io/input-stream f)]
      (loop [num 0, result []]
        (case (.readNBytes dir buf 0 3)
          0 result
          3 (recur (inc num)
                   (conj result
                         (let [vol (bit-shift-right (bit-and (aget buf 0) 0xff) 4)
                               loc (bit-or (bit-shift-left (bit-and (aget buf 0) 0x0f) 16)
                                            (bit-shift-left (bit-and (aget buf 1) 0xff) 8)
                                            (bit-and (aget buf 2) 0xff))]
                           (if (and (= vol 0x0f) (= loc 0xfffff))
                             { :num num :present false }
                             { :num num :volume vol :location loc }))))
          (throw (Exception. "Bad format of DIR file!")))))))

(defn v2-maps [root]
  "Read the SNDDIR PICDIR, etc for a v2 game"
  (reduce (fn [result [k fname]]
            (assoc result k (parse-v2-map (io/file root fname))))
          { :volume-fmt "VOL.%d" }
          [[:sound "SNDDIR"], [:pic "PICDIR"],
           [:view "VIEWDIR"], [:logic "LOGDIR"]]))

(defn determine-v3-prefix [root]
  "Figure out what the prefix string on the resource
files is, in the v3 game at ROOT."
  (let [fls (file-seq root)
        volf (first (filter #(.endsWith (.getName %) "VOL.0") fls))
        pfx (.substring volf 0 (- (.length volf) 5))]
    (if (.exists (io/file root (str pfx "DIR")))
      pfx
      (throw (Exception. "Can't determine game prefix!")))))
        
(defn v3-maps [root]
  "Read the combined DIR file for v3 maps"
  (let [prefix (determine-v3-prefix root)]
    {
     :name prefix,
     :volume-fmt (str prefix "VOL.%d"),
     }))

(defn read-game-metadata [key root-dir]
  "Determine the AGI version and load the resource lists. TODO: actually do that"
  (let [root (io/file root-dir)
        version (determine-game-version root)
        vernum  (Double. (if (> (.length version) 5)
                           (.concat (.substring version 0 5)
                                    (.substring version 6))
                           version))]
    (merge 
     {
      :name (str key),
      :dir root,
      :version version,
      :vernum vernum,
      }
     (if (< vernum 3) 
       (v2-maps root)
       (v3-maps root)))))

(defn add-game [game root-dir]
  "Register a GAME with a given ROOT-DIR. GAME will be used to
refer to the game for the rest of the resource-loading functions."
  ;; TODO: check that game is a keyword, and root-dir is a java.io.File
  ;; TODO: check if the directory exists, and looks like an AGI game.
  (swap! games assoc game (read-game-metadata game root-dir)))
   
(def resource-lru-cache
  "A LRU cche of the last few resources loaded."
  (atom (lru/make-lru 50)))

(defn load-v2-resource [game type num]
  "Read a v2 game resource or type TYPE and number NUM from GAME"
  (let [gspec (game-spec game)
        dir (type gspec)
        maxn (count dir)]
    (if (>= num maxn)
      nil
      (let [{:keys [location volume]} (dir num)
            vol (game-volume gspec (or volume 1))]
        (if volume
          (with-open [volfile (java.io.RandomAccessFile. vol "r")]
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
          nil)))))

(defn resource-key [game type num]
  "Build a LRU-cache key for a resource"
  (str num type game))

(defn resource-count [game type]
  "Look up the number of resources of type TYPE there are in GAME"
  (-> game game-spec type count))

(defn load-resource [game type num]
  "Load the resource number NUM from GAME of the given TYPE."
  (let [gspec (game-spec game)
        key (resource-key game type num)]
    (or (lru/lookup @resource-lru-cache key)
        (let [res
              (if (< (:vernum gspec) 3)
                (load-v2-resource gspec type num)
                (load-v2-resource gspec type num))] ;; todo: handle v3...
          (swap! resource-lru-cache lru/conj key res)
          res))))

