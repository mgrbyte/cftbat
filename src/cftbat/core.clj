(ns cftbat.core
  "Clojure for the Brave and True, exercises and hacking."
  (:require [clojure.pprint :refer (pprint)]
            [cftbat.exercises :as exercises]
            [cftbat.hobbits :as hobbits]
            [cftbat.vampires :as vamps]
            [cftbat.genesis :as genesis]
            [clojure.java.io :as io]))

(def vampire-suspects (io/resource "vampire-suspects.csv"))

(def asym-body-parts [{:name "head" :size 3}
                      {:name "left-eye" :size 1}
                      {:name "left-ear" :size 1}
                      {:name "mouth" :size 1}
                      {:name "nose" :size 1}
                      {:name "neck" :size 2}
                      {:name "left-shoulder" :size 3}
                      {:name "left-upper-arm" :size 3}
                      {:name "chest" :size 10}
                      {:name "back" :size 10}
                      {:name "left-forearm" :size 3}
                      {:name "abdomen" :size 6}
                      {:name "left-kidney" :size 1}
                      {:name "left-hand" :size 2}
                      {:name "left-knee" :size 2}
                      {:name "left-thigh" :size 4}
                      {:name "left-lower-leg" :size 3}
                      {:name "left-achillies" :size 1}
                      {:name "left-foot" :size 2}])

(defn -main
  "I do Hobbit violence!."
  [& args]
  (pprint (hobbits/hit asym-body-parts))
  (pprint (exercises/symmetrize-body-parts asym-body-parts 4))
  (def suspects (vamps/glitter-show (slurp vampire-suspects)))
  (def suspect-names (vamps/suspect-names suspects))
  (def suspects (vamps/append-suspect
                 suspects
                 {:name "Hillary Clinton" :glitter-index 7}))
  (print "CSV:\n")
  (print (vamps/to-csv suspects)))


