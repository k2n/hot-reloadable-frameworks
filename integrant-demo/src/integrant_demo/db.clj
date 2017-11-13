(ns integrant-demo.db
  (:require [clojure.java.io :as io]
            [integrant.core :as ig]
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

(defmethod ig/init-key :integrant-demo.db/conn [_ {:keys [conf]}]
  (open-connection conf))

(defmethod ig/halt-key! :integrant-demo.db/conn [_ conn]
  (.close conn))
