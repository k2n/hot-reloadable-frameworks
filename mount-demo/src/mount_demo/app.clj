(ns mount-demo.app
  (:require [mount.core :refer [start]]
            [mount-demo.conf :refer [conf]]
            [mount-demo.db :refer [conn]]
            [clojure.java.io :as io]
            [taoensso.timbre :as log]))

(defn start-app []
  (start))

(defn show-app []
  (log/info "conf:" conf)
  (log/info "connection: " conn))
