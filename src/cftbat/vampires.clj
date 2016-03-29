(ns cftbat.vampires
  (:require [clojure.string :as str]))


(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def not-nil? (complement nil?))

(defn is-valid
  [value-type value]
  (and (not-nil? value) (instance? value-type value)))

(def conversions {:name identity
                  :glitter-index str->int})

(def validators {:name (partial is-valid String)
                 :glitter-index (partial is-valid Number)})
 
(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse-suspects
  [lines]
  (map #(str/split % #",") (str/split lines #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10 }."
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(defn glitter-filter
  [min-glitter records]
  (filter #(>= (:glitter-index %) min-glitter) records))

;; Chapter 4, Exercise 1
(defn suspect-names
  [suspects]
  (map :name suspects))

(defn fmap
  "Return a new hash map applying f to each value. (list comp version)"
  [mapping f]
  (into {} (for [[k v] mapping] [k (f v)])))

(defn fmap-r
  "Return a new hash map applyin f to each value. (reduce version)"
  [mapping f]
  (reduce (fn [new-map [key val]]
            (assoc new-map key (f val)))
          {}
          mapping))

(defn validate-suspect
  [sus-validators suspect]
  (if (every? #(true? %) (map #((% sus-validators) (% suspect)) (keys suspect)))
    suspect
  (throw (Exception. "Invalid suspect"))))

(defn append-suspect
  [suspects suspect]
  (conj suspects (validate-suspect validators suspect)))

(defn to-csv
  [suspects]
  (str (str/join "\n" (for [suspect suspects]
                        (let [susvals (vals suspect)]
                          (str/join "," (map str susvals)))))
       "\n"))

(defn glitter-show
  [string]
  (glitter-filter 3 (mapify (parse-suspects string))))
