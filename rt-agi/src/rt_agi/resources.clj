(ns rt-agi.resources
  (:require [clojure.java.io :as io]
            [rt-agi.lru :as lru]))

;; track the games we've heard about
(def games (atom {}))

(defn add-game [game root-dir]
  "Register a GAME with a given ROOT-DIR. GAME will be used to
refer to the game for the rest of the resource-loading functions."
  ;; TODO: check that game is a keyword, and root-dir is a java.io.File
  ;; TODO: check if the directory exists, and looks like an AGI game.
  (swap! games assoc game (io/file root-dir)))
   
;; cache all loaded sound maps
(def sound-maps "A cache of loaded sound maps." (atom {}))

(defn read-sound-map [game]
  "Read the sound map for GAME from disk."
  (let [buf (byte-array 3)]
    (with-open [snddir (io/input-stream (io/file (@games game) "SNDDIR"))]
      (loop [result []]
        (case (.readNBytes snddir buf 0 3)
          0 result
          3 (recur (conj result
                         (let [vol (bit-shift-right (bit-and (aget buf 0) 0xff) 4)
                               loc (bit-or (bit-shift-left (bit-and (aget buf 0) 0x0f) 16)
                                            (bit-shift-left (bit-and (aget buf 1) 0xff) 8)
                                            (bit-and (aget buf 2) 0xff))]
                           (if (and (= vol 0x0f) (= loc 0xfffff))
                             { :present false }
                             { :volume vol :location loc }))))
          (throw (Exception. "Bad format of SNDDIR file!")))))))


(defn sound-map [game]
  "Fetch the sound-map for the given game, and cache it."
  (or (@sound-maps game)
      (let [smap (read-sound-map game)]
        (swap! sound-maps assoc game smap)
        smap)))


(defn count-resources [game type]
  "Give the number of resources for the given TYPE (:pic :view :sound)"
  (case type
    :sound (count (sound-map game))
    0))

(def sound-lru-cache
  "A LRU cche of the last few sounds loaded."
  (atom (lru/make-lru 5)))

(defn parse-sound [buff]
  "Parse an AGI sound resource. TODO: actually parse it."
  buff)

(defn read-sound [game num]
  "Read the sound resource number NUM from GAME"
  (let [smap (sound-map game)]
    (if (>= num (count smap))
      nil
      (let [{:keys [location volume]} (smap num)
            fname (format "VOL.%d" volume)]
        (if volume
          (with-open [volfile (java.io.RandomAccessFile.
                               (io/file (@games game) fname)
                               "r")]
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
                (parse-sound buff))))
          nil)))))

(defn load-resource [game type num]
  "Load the resource number NUM from GAME of the given TYPE."
  (case type
    :sound
    (let [key {:game game :num num}]
      (or (lru/lookup @sound-lru-cache key)
          (let [snd (read-sound game num)]
            (swap! sound-lru-cache lru/conj! key snd)
            snd)))
    nil))
