(ns {{name}}.routes
  (:require [clojure.java.io :as io]
            [bidi.ring :refer [->ResourcesMaybe]]
            [{{name}}.db :as d]))

(defn edn-response [data & [session status]]
  (let [r {:status (or status 200)
           :headers {"Content-Type" "application/edn"}
           :body (pr-str data)}]
    (if session (assoc r :session session) r)))

(defn app [_]
  {:status 200 :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (io/input-stream (io/resource "public/index.html"))})

(defn init [] {:schedule (into [] (d/schedule))})

(def routes
  ["/" [["api/v1/"
         [[:get [["health" (fn [_] (edn-response "ok"))]]]
          [:get [["init" (fn [r] (edn-response (init)))]]]]]
        ["" (->ResourcesMaybe {:prefix "public/"})]
        [true app]]])
