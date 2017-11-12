(ns ss-component-demo.components.db
  (:require [com.stuartsierra.component :as component]
            [clojure.java.io :as io]
            [taoensso.timbre :as log]
            [ss-component-demo.components.conf :as c]))

(defn connection
  "本来はJDBC Connectionを返す。今はダミーとして
  OutputStreamを返す。"
  [url username password]
  (log/info "DB connection info:" url username password)
  (io/output-stream "target/db.file"))

(defrecord Db [conf conn]
  component/Lifecycle
  (start [component]
    (log/info "Starting DB...")
    (let [{:keys [url username password]} (:db (c/get-conf conf))]
      (assoc component :conn (connection url username password))))
  (stop [component]
    (log/info "Stopping DB...")
    (.close conn)
    (dissoc component :conn)))

(defn new-db []
  (map->Db {}))
