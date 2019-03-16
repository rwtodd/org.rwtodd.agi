(ns org.rwtodd.agi-game.view)
;;   (:require []))

(defn read-u16
  "Read a 16bit number from two 8-bit numbers"
  [low high]
  (bit-or (bit-shift-left (bit-and high 0xff) 8) (bit-and low 0xff)))

(defn read-u16s
  "Read a series of u16s from a seq of u8s"
  [n coll]
  (into []
        (comp (partition-all 2)
              (map #(apply read-u16 %))
              (take n))
        coll))

(defn extract-desc
  "Extract the description from a view."
  [source loc]
  (when-not (zero? loc)
    (->> source
         (drop loc)
         (take-while #(not (zero? %)))
         (map char)
         (apply str))))
         
(defn parse-view
  "Parse a view resource."
  [source]
  (let [nloops   (nth source 2)
        desc-loc (read-u16 (nth source 3) (nth source 4))
        loop-loc (read-u16s nloops (drop 5 source))]
    {
     :desc (extract-desc source desc-loc)
     :loops loop-loc
     }))
