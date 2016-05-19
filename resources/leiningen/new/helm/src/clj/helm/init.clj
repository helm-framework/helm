(ns {{name}}.init (:require [{{name}}.db :as d]))

(defn setup-db []
  (when-not (d/db-exists?)
    ;; Setup the tables
    (d/user-create-table!)
    ;; Setup Data
    )
  :done)
