(ns {{name}}.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [cljs.core.async :refer [put! chan <! >! timeout]]
            [om.dom :as dom]
            [cljs-http.client :as http]
            [{{name}}.state :refer [app-state]]
            [{{name}}.routes :as routes]))

(enable-console-print!)

(defui App
  static om/IQuery
  (query [this] '[:msg])
  Object
  (render [this]
    (dom/div #js {:className "container-fluid"}
      (dom/h1 nil (om/get-props this :msg)))))

(defn read [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ value] (find st key)]
      {:value value}
      {:value :not-found})))

(defn mutate [{:keys [state] :as env} key params]
  (case key
    forward {:value {:keys [:current/month]}
              :action #(swap! state update-in [:current/month] inc)}
    {:value :not-found}))

(def reconciler
  (om/reconciler
    {:state app-state
     :parser (om/parser {:read read :mutate mutate})}))

(defn main []
  (go (let [init (:body (<! (http/get "/api/v1/init")))
            loc {:view (routes/match-route (.. js/window -location -pathname))}]
        (swap! app-state #(merge % init loc))))
  (om/add-root! reconciler App (gdom/getElement "app")))

(.addEventListener js/window "popstate"
  #(swap! app-state assoc :view (routes/match-route (.. js/window -location -pathname))))

(main)
