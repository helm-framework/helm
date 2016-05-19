(ns {{name}}.util)

(defn index-of [col v] (first (keep-indexed #(when (= v %2) %1) col)))
