(ns integrant-demo.conf
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]))

(defmethod ig/init-key :conf/conf [_ {:keys [conf]}]
  (log/info "initialize :conf/conf" conf)
  conf)
