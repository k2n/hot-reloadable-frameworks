(ns user
  (:require [mount.core :refer :all]
            [ns-tracker.core :refer [ns-tracker]]
            [mount-demo
             [conf]
             [db]]))

(def modified-namespaces
  (ns-tracker ["src" "test" "resources"]))

(defn reload []
  (doseq [ns-sym (modified-namespaces)]
    (require ns-sym :reload)))

(defn reset [args]
  (stop args)
  (start args))
