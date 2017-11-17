(ns ss-component-demo.components.app
  (:require [com.stuartsierra.component :as component]
            [taoensso.timbre :as log]))

(defrecord App [conf db])

(defn new-app []
  (map->App {}))

(defn show [this]
  (log/info "DB Connection" (get-in this [:db :conn]))
  (log/info "app conf:" (get-in this [:conf :conf :app])))
