(ns cftbat.exercises
  (:require
   [clojure.string :as string]
   [clojure.pprint :refer (pprint)]))

(defn simple-things
  "Do some basic things in Clojure.

  Chapter 3, Exercise 1."
  []
  (pprint (str "Hi" " " "There"))
  (pprint (vector 1 2 3))
  (pprint (hash-map :x 1 :y 2))
  (pprint (hash-set :x :x :y :z))
  nil)

(defn plus-100
  "Adds 100 to the given number.

  Chapter 3, Exercise 2.
  "
  [number]
  (+ number 100))


(defn dec-maker
  "Return a function which decrements a number by a given number.

  Chapter 3, Exercise 3."
  [dec-by]
  #(- % dec-by))


(defn mapset
  "Return a set of the result of mapping func to items.

  Chapter 3, Exercise 4."
  [func items]
  (set (map func items)))


(defn alien-n-matching-part
  [part n]
  (into [] (map
            #(array-map
              :name (string/replace (:name part) #"^left-" (str % "-"))
              :size (:size part)) (take n (drop 1 (range))))))


(defn symmetrize-body-parts
  "Symmetrize body parts of a create with N-PARTS given an initial configuration.
  
  Chapter 3, Exercise 5 + 6."
  [body-parts n-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (into #{} (alien-n-matching-part part n-parts))))
          []
          body-parts))


(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))
