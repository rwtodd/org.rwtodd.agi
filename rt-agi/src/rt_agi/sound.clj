(ns rt-agi.sound)
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

(defn adjacent-difference
  "Compute all adjacent differences of COLL"
  [coll]
  (map (fn [[b a]] (- a b)) (partition 2 1 coll)))

(defn uneven-partition
  "Partition COLL into groups of sizes given in NS, followed by any remaining
  data."
  [ns coll]
  (loop [ns ns, result [], coll coll]
    (if (empty? ns)
      (conj result coll)
      (let [n (first ns)]
        (recur (rest ns) (conj result (take n coll)) (drop n coll))))))

(defrecord Tone [duration freq attenuation])
(defn parse-tone
  "Parse a single tone instruction."
  [durl durh d1 d2 d3]
  (let [dur (read-u16 durl durh)
        freq (bit-or (bit-shift-left (bit-and d1 0x3f) 4)
                     (bit-and d2 0x0f))
        atten (bit-and d3 0x0f)]
    (if (= dur 0xffff)
      nil
      (->Tone dur freq atten))))

(defn parse-tones
  "Parse a series of AGI tones"
  [src]
  (into [] (comp (map #(apply parse-tone %))
                 (filter identity))
        (partition 5 5 (repeat 0) src)))

(defrecord Noise [duration freq noise attenuation])
(defn parse-one-noise
  "Parse a single noise instruction."
  [durl durh _ d2 d3]
  (let [dur (read-u16 durl durh)
        noise-char (if (zero? (bit-and d2 0x04)) :linear :white)
        freq ([0x10 0x20 0x40 0] (bit-and d2 0x03))
        atten (bit-and d3 0x0f)]
    (if (= dur 0xffff)
      nil
      (->Noise dur freq noise-char atten))))

(defn parse-noise
  "Parse a series of AGI noise instructions"
  [src]
  (into [] (comp (map #(apply parse-one-noise %))
                 (filter identity))
        (partition 5 5 (repeat 0) src)))

(defn sound-metadata
  "Derive metadat from the channels of a sound resource"
  [channels]
  (let [len (apply max
                   (map (fn [chan] (reduce #(+ %1 (.duration %2)) 0 chan))
                        channels))
        noisey (some #(< (.attenuation %) 15)
                     (channels 3))]
    { :duration len, :noisey noisey }))

(defn parse-sound
  "Parse the raw bytes of a sound resource."
  [source]
  (let [[t1 t2 t3 nz]
        (uneven-partition (adjacent-difference (read-u16s 4 source))
                          (drop 8 source))
        channels [(parse-tones t1)
                  (parse-tones t2)
                  (parse-tones t3)
                  (parse-noise nz)]]
    (assoc (sound-metadata channels) :channels channels)))


