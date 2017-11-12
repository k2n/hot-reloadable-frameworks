(ns mount-demo.conf
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [mount.core :refer [defstate]]))

;; ns-trackerではresourceの変更を検知できないため、
;; clj内にデータを移動
#_(defn load-conf []
    (-> "app-conf.edn"
        io/resource
        slurp
        edn/read-string))

(defn load-conf []
  {:db {:url      "jdbc:postgresql://localhost:5432/mydb2"
        :username "postgres"
        :password "password123"}})

(defstate conf :start (load-conf))
