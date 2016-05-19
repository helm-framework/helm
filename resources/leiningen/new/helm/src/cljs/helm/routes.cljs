(ns {{name}}.routes
  (:require [om.core :as om :include-macros true]
            [bidi.bidi :as bidi]))

(def routes
  ["/"
   {"" :home
    [[ #".*" :user] "/"] :user}])

(def url (partial bidi/path-for routes))

(def match-route (partial bidi/match-route routes))

(defn go! [data view]
  (let [h js/window.history
        view (if (vector? view) view (vector view))
        l (apply url view)
        l (if (= l "") "/" l)]
    (.pushState h {} nil l)
    ;;(om/update! data :view (match-route l))
    ))

(defn extract [] (match-route (.. js/window -location -pathname)))
