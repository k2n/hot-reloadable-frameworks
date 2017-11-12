(ns integrant-demo.system
  (:require [integrant.core :as ig]
            [integrant-demo.components :refer [components]]
            [integrant-demo.conf]
            [integrant-demo.core]
            [integrant-demo.db]))

(defn init []
  (ig/init components))

(defn halt! [system]
  (ig/halt! system))

(defn start [system]
  (ig/resume components system))

(defn stop! [system]
  (when system (ig/suspend! system)))
