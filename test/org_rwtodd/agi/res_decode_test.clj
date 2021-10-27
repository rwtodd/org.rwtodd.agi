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
