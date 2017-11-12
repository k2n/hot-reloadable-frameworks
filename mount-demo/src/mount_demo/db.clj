(ns mount-demo.db
  (:require [mount-demo.conf :refer [conf]]
            [mount.core :refer [defstate]]
            [clojure.java.io :as io]
            [taoensso.timbre :as log]))

(defn connection
  "本来はJDBC Connectionを返す。今はダミーとして
  OutputStreamを返す。"
  [url username password]
  (log/info "DB connection info:" url username password)
  (io/output-stream "target/db.file"))

(defn open-connection [conf]
  (log/info "opening a connection...")
  (let [{:keys [url username password]} (:db conf)]
    (connection url username password)))

(defn close-connection [conn]
  (.close conn)
  (log/info "connection closed."))

;; リロード時に最初のコネクションを使い続けたければ ^{:on-reload :noop} メタデータを追加
(defstate conn
  :start (open-connection conf)
  :stop (close-connection conn))
