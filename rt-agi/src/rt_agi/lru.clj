(ns rt-agi.lru)

(defn make-lru [size]
  "Create an lru cache of max-size size."
  {
   :vals {}                        ; map for fast lookup
   :order (vec (repeat size nil))  ; keys to replace
   :idx (cycle (range size))       ; index to replace next
   })

(defn conj [{:keys [idx vals order] :as lru} key val]
  "Add VAL to the lru cache, under key KEY."
  (let [oldest (order (first idx))]
    {
     :vals (assoc (dissoc vals oldest) key val)
     :order (assoc order (first idx) key)
     :idx (rest idx)
     }))

(defn lookup [lru key]
  "Lookup KEY in LRU, returning the value if found."
  ((:vals lru) key))
