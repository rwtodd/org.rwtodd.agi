(ns org.rwtodd.agi-game.objects)
;;   (:import (java.awt.image BufferedImage IndexColorModel)))
;;   (:require []))

(defn read-u16
  "Read a 16bit number from two 8-bit numbers"
  [low high]
  (bit-or (bit-shift-left (bit-and high 0xff) 8) (bit-and low 0xff)))

(defn parse-one
  [[low high room] start-offs names]
  (let [name-offs (- (read-u16 low high) start-offs)
        name      (String. 
                   (byte-array (take-while #(not (zero? %))
                                           (drop name-offs names)))
                   java.nio.charset.StandardCharsets/US_ASCII)]
    { :starting-room (bit-and room 0xff), :name name }))

(defn parse-objects
  "Parse the AGI Object file format. It is expected to be decoded already,
  if necessary."
  [objs]
  (let [words-start       (read-u16 (nth objs 0) (nth objs 1))
        max-anim          (nth objs 2)
        [objlst objnames] (split-at words-start (drop 3 objs))]
    {
     :max-animated max-anim
     :objects (into []
                    (comp
                     (partition-all 3)
                     (map #(parse-one % words-start objnames)))
                    objlst)
     }))
