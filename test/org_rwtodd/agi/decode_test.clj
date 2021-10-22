(ns org-rwtodd.agi.decode-test
  (:require [clojure.test :refer :all]
            [org-rwtodd.agi.decode :as d]))

(deftest test-lzw
  (testing "lzw expansion"
    (is (= (vec (d/expand-lzw [87 0] 1)) [87]) "trivial expansion")
    (is (= (vec (d/expand-lzw [87 178 76 81 33 16] 6)) [87 89 83 42 87 89]) "basic expansion")))

