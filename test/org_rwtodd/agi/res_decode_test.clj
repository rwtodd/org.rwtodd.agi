(ns org-rwtodd.agi.res-decode-test
  (:require [clojure.test :refer :all]
            [org-rwtodd.agi.res-decode :as d]))

(deftest test-reading
  (let [arr (byte-array [1 2 3 4])]
    (testing "16-bit little endian"
      (is (== (d/read-16-le arr 0) 0x201))
      (is (== (d/read-16-le arr 1) 0x302))
      (is (== (d/read-16-le arr 2) 0x403)))))
      
(deftest test-lzw
  (testing "lzw expansion"
    (is (= (vec (d/expand-lzw [87 0] 1)) [87]) "trivial expansion")
    (is (= (vec (d/expand-lzw [87 178 76 81 33 16] 6)) [87 89 83 42 87 89]) "basic expansion")))

(deftest test-avis
  (testing "avis durgan decode"
    (let [test-pattern
          (byte-array [65, 119, 107, 112, 36, 65, 115, 117, 111, 104, 100, 74, 122, 100,
                       125, 47, 84, 100, 96, 116, 117, 123, 87, 97, 113, 106, 58, 95, 105,
                       111, 121, 126, 78, 96, 84, 74, 87, 5, 98, 82, 90, 78, 75, 69, 109,
                       91, 71, 92, 16, 117, 71, 65, 83, 84, 88, 118, 78, 80, 73, 27, 120,
                       72, 76, 88, 33, 47, 3, 53, 45, 54, 102, 3, 61, 59, 45, 42, 34, 12,
                       56, 38, 35, 113, 22, 38, 38, 50, 55, 57, 25, 47, 51, 40, 124, 25,
                       43, 45, 7, 0, 12, 34, 18, 12, 21, 71, 44, 28, 24, 12, 13, 3, 47, 25,
                       25, 2, 82, 55, 1, 7, 17, 22, 22, 56, 12, 18, 15, 93, 58, 10, -14,
                       -26, -29, -19, -59, -13, -17, -12, -88, -51, -1, -7, -21, -20, -32,
                       -50, -26, -8, -31, -77, -48, -32, -28, -16, -7, -9, -37, -19, -11,
                       -18, -66, -37, -43, -45, -59, -62, -54, -28, -48, -50, -37, -119,
                       -18, -34, -34, -54, -49, -63, -15, -57, -37, -64, -108, -15, -61,
                       -59, -33, -40, -44, -6, -54, -44, -51, -97, -124, -76, -80, -92,
                       -91, -85, -121, -79, -95, -70, -22, -113, -71, -65, -87, -82, -66,
                       -112, -92, -70, -89, -11, -110, -94, -86, -66, -69, -75, -99, -85,
                       -73, -84, -64, -91, -105, -111, -125, -124, -120, -90, -98, -128,
                       -103, -53, -88, -104, -100, -120, -111, -97, -77, -123, -99, -122,
                       -42, -77, -115, -117, -99, -102, -110, -68, -120, -106])]
      (d/avis-durgan! test-pattern)
      (is (= (seq test-pattern) (map unchecked-byte (range 256)))))))

(deftest test-asciiz
  (testing "decode asciiz string"
    (is (= (d/read-asciiz (byte-array [97 98 99 100 0 101 102 103]) 1) "bcd"))))