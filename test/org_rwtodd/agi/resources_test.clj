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

(deftest test-words-tok
  ;; xc == transform character   fc == final character
  (letfn [(xc [ch] (->> ch int (bit-xor 0x7f)))
          (fc [ch] (bit-or 0x80 (xc ch)))]
    (testing "one word only"
      (let [dict  (r/parse-words-tok (byte-array [0 2 0 (xc \a) (xc \b) (fc \c) 1 0]))]
        (is (== (count dict) 1))
        (is (== (get dict "abc" 0) 256))))        
    (testing "two words w/overlap"
      (let [dict  (r/parse-words-tok (byte-array [0 2 0 (xc \a) (xc \b) (fc \c) 1 0
                                                       1 (xc \z) (fc \z) 0 20]))]
        (is (== (count dict) 2))
        (is (== (get dict "abc" 0) 256))
        (is (== (get dict "azz" 0) 20))))
    (testing "three words w/ and w/o overlap"
      (let [dict  (r/parse-words-tok (byte-array [0 2 0 (xc \a) (xc \b) (fc \c) 1 0
                                                   1 (xc \z) (fc \z) 0 20
                                                  0 (xc \p) (xc \n) (fc \g) 0 30]))]
        (is (== (count dict) 3))
        (is (== (get dict "abc" 0) 256))
        (is (== (get dict "azz" 0) 20))
        (is (== (get dict "png" 0) 30))))))

(deftest test-resource-parse
  (let [rdir (r/parse-resdir [ 0xff 0xff 0xff 0x12 0x34 0x56 ])]
    (testing "all 0xF's == not there"
      (is (nil? (nth rdir 0))))
    (testing "lookup specific resource"
      (is (= (nth rdir 1) (r/->ResourceEntry 1 0x23456))))))
