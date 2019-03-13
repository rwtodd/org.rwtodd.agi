(ns rt-agi.lru)

(defn make-lru
  "Create an lru cache of max-size size."
  [size]
  {
   :vals {}                        ; map for fast lookup
   :order (vec (repeat size nil))  ; keys to replace
   :idx (cycle (range size))       ; index to replace next
   })

(defn conj
  "Add VAL to the lru cache, under key KEY."
  [{:keys [idx vals order] :as lru} key val]
  (let [oldest (order (first idx))]
    {
     :vals (assoc (dissoc vals oldest) key val)
     :order (assoc order (first idx) key)
     :idx (rest idx)
     }))

(defn lookup
  "Lookup KEY in LRU, returning the value if found."
  [lru key]
  ((:vals lru) key))
