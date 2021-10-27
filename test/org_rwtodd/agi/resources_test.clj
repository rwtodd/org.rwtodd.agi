(ns org-rwtodd.agi.resources-test
  (:require [clojure.test :refer :all]
            [org-rwtodd.agi.resources :as r]))

(deftest test-version-extract
  (testing "trivial version extraction"
    (is (== (r/numeric-version (r/extract-version (.getBytes "1.111"))) 1.111))
    (is (== (r/numeric-version (r/extract-version (.getBytes "1.111.111"))) 1.111111)))
  (testing "cutoff version extraction"
    (is (== (r/numeric-version (r/extract-version (.getBytes "1.111.1"))) 1.111))
    (is (== (r/numeric-version (r/extract-version (.getBytes "1.111.11"))) 1.111)))
  (testing "searches for version"
    (is (== (r/numeric-version (r/extract-version (.getBytes "aj j1.111.1bcbx"))) 1.111))
    (is (== (r/numeric-version (r/extract-version (.getBytes " x 2.345.678 xx"))) 2.345678)))
  (testing "nil when version missing"
    (is (nil? (r/extract-version (.getBytes "aj j1-111.1bcbx"))))
    (is (nil? (r/extract-version (.getBytes " x 2.3v5.67~ xx")))))
  (testing "keeps first version number"
    (is (== (r/numeric-version (r/extract-version (.getBytes "g 1.111.1 1.234 x"))) 1.111))
    (is (== (r/numeric-version (r/extract-version (.getBytes "1x111.123x 9.999"))) 1.123))))

(deftest test-resource-parse
  (let [rdir (r/parse-resdir [ 0xff 0xff 0xff 0x12 0x34 0x56 ])]
    (testing "all 0xF's == not there"
      (is (nil? (nth rdir 0))))
    (testing "lookup specific resource"
      (is (= (nth rdir 1) (r/->ResourceEntry 1 0x23456))))))
