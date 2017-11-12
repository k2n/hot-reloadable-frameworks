(ns ss-component-demo.components.conf
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [taoensso.timbre :as log]
            [com.stuartsierra.component :as component]))

;; Recordは既定のキーを持つmapを生成するための手段
;; さらに、プロトコルの実装を含むことができる
(defrecord Conf []
  component/Lifecycle ;;Javaのimplements Interfaceに相当
  ;;Protocolの実装
  (start [component]
    (log/info "Starting Conf...")
    ;;component mapに{:conf v}を追加したmapを返す
    (assoc component :conf (-> "app-conf.edn"
                               io/resource
                               slurp
                               edn/read-string)))
  ;;Protocolの実装
  (stop [component]
    (log/info "Stopping Conf...")
    ;; component mapから:confキーとその値を削除したmapを返す
    (dissoc component :conf)))

(defn new-conf []
  ;;レコードを生成するためのfunction. `defrecord`マクロが暗示的に`map->Conf`を本namespace内に追加している
  (map->Conf {}))

;; Componentのデータを用いた外部向け関数の例
(defn get-conf [this]
  (:conf this))
