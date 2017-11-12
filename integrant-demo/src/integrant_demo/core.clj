(ns integrant-demo.core
  (:require [integrant.core :as ig]
            [taoensso.timbre :as log]))

(defmethod ig/init-key :core/myfn [_ {:keys [conn]}]
  (log/info "Connection:" conn)
  "connから取得したデータをこちらで保持")

(defmethod ig/resume-key :core/myfn [key opts old-opts old-impl]
  "一時停止からの復帰時に別の振る舞いを定義することも可能")
