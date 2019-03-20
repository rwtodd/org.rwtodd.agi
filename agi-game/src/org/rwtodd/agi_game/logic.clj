(ns org.rwtodd.agi-game.logic)

(defn read-u16
  "Read a 16bit number from two 8-bit numbers"
  [low high]
  (bit-or (bit-shift-left (bit-and high 0xff) 8) (bit-and low 0xff)))

(defn read-string
  "Read an ASCII string from the given location in a buffer"
  [src offs]
  (String. (byte-array (take-while #(not (zero? %)) (drop offs src)))
           java.nio.charset.StandardCharsets/US_ASCII))
              
(defn parse-logic
  "Parse a logic resource"
  [src]
  (let [logic-len     (read-u16 (nth src 0) (nth src 1))
        num-messages  (nth src (+ logic-len 2))
        message-base  (+ logic-len 3)
        messages      (into []
                            (comp
                             (drop (+ logic-len 5))
                             (take (* num-messages 2))
                             (partition-all 2)
                             (map #(+ message-base (apply read-u16 %)))
                             (map #(read-string src %)))
                            src)]
    {
     :bytecode (byte-array (take logic-len (drop 2 src))),
     :messages messages
     }))
