(ns mount-demo.core
  (:require [mount-demo.db :refer [conn]]
            [taoensso.timbre :as log]))

(log/info "connection: " conn)
