(ns user
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [integrant.core :as ig]
            [integrant-demo.system :as system]))

(def system nil)

(defn init []
  (alter-var-root #'system (constantly (system/init))))

(defn halt []
  (alter-var-root #'system (constantly (system/halt! system))))

(defn start []
  (alter-var-root #'system (constantly (system/start system))))

(defn stop []
  (system/stop! system))

(defn reset []
  (halt)
  (refresh :after 'user/init))

(defn restart []
  (stop)
  (start))
