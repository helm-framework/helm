(ns {{name}}.server
  (:require [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [bidi.ring :as bidi]
            [immutant.web :as web]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.session.cookie :refer [cookie-store]]
            [{{name}}.util :refer [env wrap-edn-params]]
            [{{name}}.db :as db]
            [mount.core :as mount]
            [{{name}}.init :refer [setup-db]]
            [{{name}}.routes :refer [routes]])
  (:gen-class))

(def app
  (-> routes
    (bidi/make-handler)
    (wrap-defaults api-defaults)
    (wrap-edn-params)
    (wrap-session
      {:cookie-attrs {:max-age 360000000}
       :store (cookie-store {:key "{{cookie-key}}"})
       :cookie-name "{{name}}"})))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 3000))]
    (print "Starting web server on port" port ".\n")
    (mount/start)
    (setup-db)
    (if (:IS_DEV env)
      (web/run-dmc app {:port port :host "0.0.0.0"})
      (web/run app {:port port :host "0.0.0.0"}))))
