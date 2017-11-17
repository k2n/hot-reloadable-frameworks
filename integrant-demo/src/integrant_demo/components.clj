(ns integrant-demo.components
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]))

(def components
  {:integrant-demo.conf/conf {:conf {:db {:url      "jdbc:postgresql://localhost:5432/mydb2"
                                          :username "postgres"
                                          :password "password123"}}}
   ;; ig/refでDependency Ingection
   :integrant-demo.db/conn {:conf (ig/ref :integrant-demo.conf/conf)}
   :integrant-demo.app/myfn {:conn (ig/ref :integrant-demo.db/conn)}})
