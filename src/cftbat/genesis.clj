(ns cftbat.genesis)
()
(defn mattr-comp
  "Re-implement the builtin comp function.

  Chapter 5, Exercise 2."
  ([] (mattr-comp identity))
  ([func & others]
   (fn [& args]
     (let [fns (cons func (or  others []))]
          (reduce (fn [result next-fn]
                    (next-fn result))
                  (apply (last fns) args)
                  (rest (reverse fns)))))))


(defn mattr-assoc-in
  "Re-implement builtin assoc-in function.

  Chapter 5, Exercise 3."
  [m [k & ks] v]
  (if ks
    (assoc m k (mattr-assoc-in (get m k) ks v))
    (assoc m k v)))

