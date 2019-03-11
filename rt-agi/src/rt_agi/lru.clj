(ns rt-agi.lru)

(defn make-lru [size]
  "Create an lru cache of max-size size. We assume size is on the order of 10
or so, so a simple linear search of the vals on lookup should be ok."
  (if (> size 20)
    (throw (Exception. "Max size of LRU cache is 20!"))
    { :vals () :max-size size }))

(defn conj! [lru key val]
  "Add VAL to the lru cache, under key KEY."
  (assoc lru
         :vals (doall (take (:max-size lru)
                            (cons { :key key :val val } (:vals lru))))))

(defn lookup [lru key]
  "Lookup KEY in LRU, returning the value if found."
    (:val (first (filter #(= key (:key %1)) (:vals lru)))))
