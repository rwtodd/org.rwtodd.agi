(ns org-rwtodd.agi.res-decode
  (:require [clojure.java.io :as io])
  (:import [clojure.lang IFn]
           [java.util Arrays]
           [java.nio.charset StandardCharsets]))

;; ====== File Reading / Array Decoding
(defn read-entire-file
  "Reads an entire file into a byte array"
  [path fname]
  (with-open [stream (->> fname (io/file path) io/input-stream)]
    (.readAllBytes stream)))

(defmacro read-8 [arr idx]
  `(bit-and (aget ~arr ~idx) 0xff))
(defmacro read-16-le [arr idx]
  `(bit-or (read-8 ~arr ~idx)
           (bit-shift-left (read-8 ~arr (inc ~idx)) 8)))
(defmacro read-16-be [arr idx]
  `(bit-or (bit-shift-left (read-8 ~arr ~idx) 8)
           (read-8 ~arr (inc ~idx))))
(defn aslice [arr start end]
  (map #(aget arr %) (range start end)))

;; ====== ASCIIZ
;; a few AGI locations have null-terminated strings, so we need a way to decode them
(defn read-asciiz
  "Read a null-terminated ASCII string from SRC at IDX."
  [^bytes src idx]
  (loop [last idx]
    (if (zero? (aget src last))
      (String. src idx (- last idx) StandardCharsets/US_ASCII)
      (recur (inc last)))))
   
;; ====== AVIS DURGAN
;; several portions of game data are XOR-ed against the ASCII bytes for AVIS DURGAN.
(def ^:private avis (.getBytes "Avis Durgan" StandardCharsets/US_ASCII))
(defn avis-durgan!
  ([^bytes src] (avis-durgan! src 0 (alength src)))
  ([^bytes src start end]
   (dorun (map (fn [idx b] (aset-byte src idx (bit-xor (aget src idx) b)))
               (range start end)
               (cycle avis)))))

;; ====== LZW Expansion
;; this helper class uses unsynch mutable fields for better performance, so
;; we must take care to use it only in single-threaded contexts.  That's no
;; problem, since only `expand-lzw` uses it.
(deftype TokenStreamReader
    [rf
     ^:unsynchronized-mutable ^byte width
     ^:unsynchronized-mutable ^byte read-bits-size
     ^:unsynchronized-mutable ^long hi
     ^:unsynchronized-mutable ^long overflow
     ^:unsynchronized-mutable ^long read-bits]
  IFn
  (invoke [this] (rf))
  (invoke [this result] (rf result))
  (invoke [this result input]
    (set! read-bits      (bit-or read-bits (bit-shift-left (bit-and input 0xff)
                                                           read-bits-size)))
    (set! read-bits-size (unchecked-byte (+ read-bits-size 8)))
    (if (< read-bits-size width)
      result
      (let [current (bit-and read-bits (dec (bit-shift-left 1 width)))]
        (set! read-bits-size (unchecked-byte (- read-bits-size width)))
        (set! read-bits      (unsigned-bit-shift-right read-bits width))
        (set! hi             (inc hi))
        (when (= current 256) ;; 256 == CLEAR
          (set! width    (unchecked-byte 9))
          (set! hi       257)
          (set! overflow 512))
        (when (and (>= hi overflow) (< width 11))
          (set! width    (unchecked-byte (inc width)))
          (set! overflow (bit-shift-left overflow 1)))
        (rf result current)))))

(defn- lzw-token-stream
  "A transducer that takes a series of bytes and converts them to LZW tokens
  for expansion.  Sierra's AGI was unusual in that it used 11-bit LZW, whereas
  12-bit was far more common (e.g., GIF compression)."
  [rf]
  (->TokenStreamReader rf    ;; pass along the rf
                       9     ;; initial width of 9
                       0     ;; 0 bits have been read
                       257   ;; initial dictionary idx of 257
                       512   ;; 9-bits overflows at 512
                       0))   ;; 0 current value read

;; this helper class uses unsynch mutable fields for better performance, so
;; we must take care to use it only in single-threaded contexts.  That's no
;; problem, since only `expand-lzw` uses it in a `sequence`.
(deftype TokenExpander
    [rf
     ^:unsynchronized-mutable ^long hi
     ^:unsynchronized-mutable ^short previous
     ^:unsynchronized-mutable ^shorts prefix
     ^:unsynchronized-mutable ^bytes suffix]
  IFn
  (invoke [this] (rf))
  (invoke [this result] (rf result))
  (invoke [this result input]
    (cond
      ;; the CLEAR code (256) resets the dictionary
      (= input 256)
      (do (set! previous (unchecked-short -1))
          (set! hi 257)
          result)

      ;; the EOF code (257) means we are done
      (= input 257)  ;; 257 == EOF code
      (reduced result)

      ;; anything up to `hi` is in the dictionary, and needs to
      ;; be "replayed" to the output
      (<= input hi)
      (let [token-hi?    (and (= input hi) (not= previous -1))
            start-token  (if token-hi? previous input)
            suffixes     (into []
                               (comp (take-while #(>= % 0))
                                     (map #(aget suffix %)))
                               (iterate #(aget prefix %) start-token))]
        (when (not= previous -1)
          (aset-byte  suffix hi (peek suffixes))
          (aset-short prefix hi previous))
        (set! previous (unchecked-short input))
        (set! hi (inc hi))
        (reduce rf result (if token-hi?
                            (concat (rseq suffixes) [(peek suffixes)])
                            (rseq suffixes))))

      :else (throw (ex-info "Malformed LZW Resource" {:token input :hi hi})))))

(defn- lzw-token-expansion
  "A transducer that expands input LZW according to the LZW compression
  algorithm used by Sierra."
  [rf]
  (->TokenExpander rf                                    ;; pass along the rf
                   257                                   ;; next token index is 257
                   -1                                    ;; no previous token
                   (doto (short-array 2048)              ;; first 256 are negative
                     (Arrays/fill 0 256 (unchecked-short -1)))
                   (byte-array 2048 (range 256))))       ;; first 256 are 0-255

(defn expand-lzw
  "Expands byte array SRC using the LZW algorithm variant from Sierra.  The
  result will always be of length EXPANDED-SIZE."
  [src expanded-size]
  (byte-array expanded-size
              (sequence (comp lzw-token-stream
                              lzw-token-expansion)
                        src)))

;; ====== PIC decompression
(defn expand-pic
  "Expand the byte array of PIC data, when it's compressed in V3 AGI files."
  [src expanded-size]
  ;; TBD!!!
  (byte-array expanded-size))

;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
