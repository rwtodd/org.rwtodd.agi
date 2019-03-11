(ns rt-agi.resources
  (:require [clojure.java.io :as io]))

;; track the games we've heard about
(def games (atom {}))

(defn add-game [game root-dir]
  "Register a GAME with a given ROOT-DIR. GAME will be used to
refer to the game for the rest of the resource-loading functions."
  ;; TODO: check that game is a keyword, and root-dir is a java.io.File
  ;; TODO: check if the directory exists, and looks like an AGI game.
  (swap! games assoc game (io/file root-dir)))
   
;; track the last few sound maps
;; seen
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


