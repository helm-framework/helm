(ns {{name}}.stripe (:require [clj-http.client :as client]))

(def endpoint "https://api.stripe.com/v1")

(defn api [auth path args]
  (:body
   (client/request
     {:basic-auth auth :as :json :coerce :always
      :throw-exceptions false
      :url (str endpoint path) :method (:method args)
      :form-params (dissoc args :method)})))
