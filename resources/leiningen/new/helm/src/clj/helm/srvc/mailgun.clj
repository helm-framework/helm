(ns {{name}}.mailgun (:require [clj-http.client :as client]))

(def endpoint "https://api.mailgun.net/v3")

(defn send-email [{:keys [domain api-key]} msg]
  (client/post (str endpoint "/" domain "/messages")
    {:basic-auth ["api" api-key]
     :form-params msg}))

;;;; Usage
;;;; https://documentation.mailgun.com/api-sending.html#sending
;; (let [credentials {:api-key "" :domain ""}
;;       msg {:from "" :to "" :subject "" :html ""}]
;;   (send-email credentials msg))
