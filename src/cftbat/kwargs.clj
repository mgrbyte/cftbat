(ns cftbat.kwargs
  (:require [clojure.set :as set]))

(defn foo
  [& {:keys [x y]
      :or {y 2}}]
  (+ x y))


(defn rgb
  [& {:keys [red blue green]
      :or {red 255
           blue 255
           green 255}}]
  (format "%03d-%03d-%03d" red blue green))


(defn fully-funky
  [a b c & {:keys [d e f]
            :or {d 2
                 f 1}}]
  (- (+ a b c) d e f))


(defn required-kwds
  [func-ref]
  (let [func-info (meta func-ref)
        arglist (-> func-info :arglists flatten)
        kwds (-> arglist last)
        opt (apply sorted-set (-> kwds :or keys))
        req (apply sorted-set (-> kwds :keys))]
    (set/difference req opt)))




