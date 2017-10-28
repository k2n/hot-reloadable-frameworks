(ns ring-devel-demo.handler
  (:require [clojure.string :as string]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.page :refer [html5]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defonce history (atom []))

(defroutes app-routes
  (GET "/" []
    (html5
     [:body [:h1 "ring-devel デモ"]]))

  (POST "/add-entry" [entry]
    (swap! history conj entry)
    (format "履歴:%s" (string/upper-case (apply str @history))))

  (GET "/entries" []
    (html5 [:body "履歴" [:ul (for [i @history] [:li i])]]))

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes api-defaults))
