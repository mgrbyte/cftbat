(ns cftbat.sametime
  "Concurrency examples."
  (:require [clojure.string :as str]
            [cemerick.url :refer (url url-encode)]))

(defmacro wait
  "Sleep `timeout` seconds before evaluating `body`."
  [timeout & body]
  `(do (Thread/sleep ~timeout) ~@body))

(defmacro enqueue
  ([q concurrent-promise-name concurrent serialized]
   `(let [~concurrent-promise-name (promise)]
      (future (deliver ~concurrent-promise-name ~concurrent))
      (deref ~q)
      ~serialized
      ~concurrent-promise-name))
  ([concurrent-promise-name concurrent serialized]
   `(enqueue (future) ~concurrent-promise-name ~concurrent ~serialized)))


(defn web-engine-search
  [engine term]
  (let [base-url (str "https://www." engine ".com")
        query (url-encode term)
        addr (str (url base-url (str "?q=" query)))]
    (slurp addr)))


(defn web-hit?
  [page]
  (.endsWith page "</html>"))


(def search-engines ["google" "bing"]) 

(defn web-search
  [term]
  (let [result (promise)
        pages (for [engine search-engines]
                (let [page {engine (future
                                     (deliver
                                      result
                                      (let [page (web-engine-search engine term)]
                                        (when (web-hit? page)
                                          page))))}]
                  (hash-map engine page)))]
    (first pages)))

