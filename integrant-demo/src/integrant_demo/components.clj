(ns integrant-demo.components
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]))

(def components
  {:conf/conf {:conf {:db {:url      "jdbc:postgresql://localhost:5432/mydb2"
                           :username "postgres"
                           :password "password123"}}}
   ;; ig/refでDependency Ingection
   :db/conn {:conf (ig/ref :conf/conf)}
   :core/myfn {:conn (ig/ref :db/conn)}})
