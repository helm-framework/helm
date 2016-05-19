(ns {{name}}.synchronize (:require [{{name}}.db :as d]))

(defn synchronize [{:keys [edn-params request-method] {user :user} :session}]
  (let [resp #(hash-map
                :status 200
                :headers {"Content-Type" "application/edn"}
                :body (pr-str %))
        fns {:user {:put #(d/user-update! (merge updated %))
                    :delete d/user-delete!
                    :post #(d/user-create<!
                             (merge created updated {:email (d/temp-email)}
                               d/user-default %))}}]
    (resp
      ((get-in fns [(:entity edn-params) request-method]) edn-params))))
