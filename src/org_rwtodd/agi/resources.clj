(ns org-rwtodd.agi.resources
  (:require [clojure.java.io :as io]))

(defn game-path?
  "Verify that this appears to be an AGI game directory"
  [path]
  (let [dir (io/file path)]
    (and (.isDirectory dir)
         (.exists (io/file dir "AGIDATA.OVL")))))

;; ---- support functions for `extract-version` -------------
(defn- digit? [x] (<= 48 x 57)) ;; 48-57 == ASCII 0-9
(defn- dot?   [x] (== x 46))  ;; 46 == ASCII '.'
(def ^:private  long-vernum [digit? dot? digit? digit? digit? dot? digit? digit? digit?])
(def ^:private short-vernum  [digit? dot? digit? digit? digit?])
(defn- vermatch [ver bs]  (every? true? (map #(%1 %2) ver bs)))

(defn extract-version
  "Extract the AGI version data from the provided seq of bytes, BS. Typically
   this data will come from the AGIDATA.OVL file"
  [bs]
  (let [;; get the first tail of the input which matches a short version number
        match (first (filter #(vermatch short-vernum %)
                             (reductions (fn [s _] (rest s)) bs bs)))
        
        ;; attempt to match the longer version, and take 9 chars
        ;; if successful.  Otherwise, take the short version we
        ;; already matched
        strver (apply str
                      (sequence (comp (take (if (vermatch long-vernum match) 9 5))
                                      (map unchecked-char))
                                match))]
    ;; make sure the resulting string is one of the valid lengths
    (case (count strver)
      (5 9)   strver
      (6 7 8) (.substring strver 0 5)
      nil)))

(defn numeric-version
  "Convert an AGI version string to a double. It is expected to be either
  d.ddd or d.ddd.ddd and any other format will cuase an exception."
  [sv]
  (Double/parseDouble (if (== (count sv) 9)
                        (str (.substring sv 0 5) (.substring sv 6))
                        sv)))
