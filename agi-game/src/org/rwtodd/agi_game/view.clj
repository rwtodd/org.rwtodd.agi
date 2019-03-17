(ns org.rwtodd.agi-game.view
  (:import (java.awt.image BufferedImage IndexColorModel)))
;;   (:require []))

(defonce ega-rgba [0x00 0x00 0x00 0xff
                   0x00 0x00 0xaa 0xff
                   0x00 0xaa 0x00 0xff
                   0x00 0xaa 0xaa 0xff
                   0xaa 0x00 0x00 0xff
                   0xaa 0x00 0xaa 0xff
                   0xaa 0x55 0x00 0xff
                   0xaa 0xaa 0xaa 0xff
                   0x55 0x55 0x55 0xff
                   0x55 0x55 0xff 0xff
                   0x55 0xff 0x55 0xff
                   0x55 0xff 0xff 0xff
                   0xff 0x55 0x55 0xff
                   0xff 0x55 0xff 0xff
                   0xff 0xff 0x55 0xff
                   0xff 0xff 0xff 0xff])

(defn make-palette
  "Get an EGA palette with the specified transparent color"
  [trans-color]
  (byte-array (assoc ega-rgba (+ (* trans-color 4) 3) 0x00)))

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


(defn extract-cell
  "Extract a single cell from a loop from a view."
  [loop-number source]
  (let [width        (bit-and (nth source 0) 0xff)
        height       (bit-and (nth source 1) 0xff)
        third-byte   (bit-and (nth source 2) 0xff)
        transparent  (bit-and third-byte 0x0f)
        mirrored?    (and (> (bit-and third-byte 0x80) 0)
                          (not (= loop-number
                                  (bit-and (bit-shift-right third-byte 4) 3))))
        img          (BufferedImage. width
                                     height
                                     BufferedImage/TYPE_BYTE_BINARY
                                     (IndexColorModel. 4
                                                       16
                                                       (make-palette transparent)
                                                       0
                                                       true))
        imgr         (.getRaster img)
        init-x       (if mirrored? (- width 1) 0)
        offs-x       (if mirrored? - +)]
    (loop [y 0,  x init-x, source (drop 3 source)]
      (when (< y height)
        (let [cb (first source)]
          (if (zero? cb)
            (do
              (dotimes [dx (if mirrored? (+ x 1) (- width x))]
                (.setSample imgr (offs-x x dx) y 0 transparent))
              (recur (inc y) init-x (next source)))
            (let [color (bit-shift-right cb 4)
                  times (bit-and cb 0x0f)]
              (dotimes [dx times]
                (.setSample imgr (offs-x x dx) y 0 color))
              (recur y (offs-x x times) (next source)))))))
    {
     :width width,              :height height,
     :transparent transparent,  :mirrored? mirrored?
     :img img
    }))

(defn extract-loop
  "Extract a loop from a view."
  [loop-number source]
  (let [ncells (bit-and (first source) 0xff)
        cell-loc (read-u16s ncells (rest source))]
    (into []
          (map #(extract-cell loop-number (drop % source)))
          cell-loc)))
               
  
(defn parse-view
  "Parse a view resource."
  [source]
  (when source
    (let [nloops   (nth source 2)
          desc-loc (read-u16 (nth source 3) (nth source 4))
          loop-loc (read-u16s nloops (drop 5 source))]
      {
       :desc (extract-desc source desc-loc)
       :loops (into []
                    (map-indexed #(extract-loop %1 (drop %2 source)))
                    loop-loc)
       })))
