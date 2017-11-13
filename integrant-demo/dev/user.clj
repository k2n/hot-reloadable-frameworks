(ns user
  "REPLからIntegrantを操作するための関数群。
  https://github.com/weavejester/integrant-repl を利用すれば
  プロジェクト毎に自分で記述する負担を軽減できる。"
  (:require [clojure.tools.namespace.repl :refer [refresh]]
            [integrant.core :as ig]
            [integrant-demo.system :as system]))

(def system nil)

(defn init []
  (alter-var-root #'system (constantly (system/init))))

(defn halt []
  (alter-var-root #'system (constantly (system/halt! system))))

(defn start []
  (alter-var-root #'system (constantly (system/start system))))

(defn stop []
  (system/stop! system))

(defn reset []
  (halt)
  (refresh :after 'user/init))

;; 一時停止・復帰はtools.namespace/refreshとは共存しないのではないか？
(defn restart []
  (stop)
  (start))
