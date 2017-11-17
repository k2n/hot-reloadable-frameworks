(ns integrant-demo.system
  (:require [integrant.core :as ig]
            [integrant-demo.components :refer [components]]
            ;; ig/load-namespacesを使えば下記の定義は不要
            ;[integrant-demo.app]
            ;[integrant-demo.conf]
            ;[integrant-demo.db]
))

(ig/load-namespaces components)

(defn init []
  (ig/init components))

(defn halt! [system]
  (ig/halt! system))

(defn start [system]
  (ig/resume components system))

(defn stop! [system]
  (when system (ig/suspend! system)))
