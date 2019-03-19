(ns org.rwtodd.agi-game.words)

(defn read-u16-be
  "Read a 16bit big-endian number from two 8-bit numbers"
  [high low]
  (bit-or (bit-shift-left (bit-and high 0xff) 8) (bit-and low 0xff)))

(defn last-byte? [b] (pos? (bit-and b 0x80)))
(defn byte-to-letter [b] (char (bit-and (bit-xor b 0x7f) 0x7f)))

(defn parse-word
  [src last-word]
  (let [prefix (.substring last-word 0 (nth src 0))
        sb     (StringBuilder. prefix)]
    (loop [src (drop 1 src)]
      (let [b (first src)]
        (if (nil? b)
          [ "" nil ]
          (do
            (.append sb (byte-to-letter b))
            (if (last-byte? b)
              [ (.toString sb) (next src) ]
              (recur (next src)))))))))

(defn parse-words
  "Parse an AGI Words.Tok file, which has been slurped into
  memory prior to reaching this function."
  [src]
  (loop [src (drop 52 src)
         last-word ""
         words-to-group (transient {})
         group-to-words (transient {})]
    (let [[cur-word new-src] (parse-word src last-word)
          group              (and new-src
                                  (read-u16-be (nth new-src 0) (nth new-src 1)))]
      (if (nil? new-src)
        {
         :words (persistent! words-to-group),
         :groups (persistent! group-to-words)
         }
        (recur (drop 2 new-src)
               cur-word
               (assoc! words-to-group cur-word group)
               (assoc! group-to-words
                       group
                       (conj (get group-to-words group #{}) cur-word)))))))


(defn synonyms
  "Helper function to look up the synonyms of a word."
  [words word]
  (-> words :groups (get (-> words :words (get word)))))
