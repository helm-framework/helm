(ns {{name}}.db
  (:require [conman.core :as conman]
            [mount.core :refer [defstate]]
            [clojure.java.jdbc :as jdbc]
            [{{name}}.util :refer [env]])
  (:import [java.sql
            Timestamp
            PreparedStatement]))

(def pool-spec
  {:adapter :postgresql
   :init-size 1
   :min-idle 1
   :max-idle 4
   :max-active 32
   :jdbc-url (str "jdbc:postgresql://" (:DB_HOST env) ":5432/" (:DB_NAME env)
               "?user=" (:DB_USER env) "&password=" (:DB_PASS env))})

(defstate ^:dynamic *db*
  :start (conman/connect! pool-spec)
  :stop (conman/disconnect! *db*))

(extend-type java.util.Date
  jdbc/ISQLParameter
  (set-parameter [value ^PreparedStatement stmt ^long idx]
    (.setTimestamp stmt idx (Timestamp. (.getTime value)))))

(conman/bind-connection *db* "{{name}}/queries.sql")

(defn db-exists? []
  (try
    (user-all)
    (catch org.postgresql.util.PSQLException e false)))
