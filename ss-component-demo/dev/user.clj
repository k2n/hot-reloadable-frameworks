(ns user
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer [refresh]]
            [ss-component-demo.systems :as systems]))

;; Componentは`def`の定義を禁止しているが、component SystemMapを保持するこの値だけは
;; 例外
(def system nil)

(defn init []
  ;; 値の変更はアトミックなので、仮に別のスレッドがsystemを参照していても
  ;; 変更途中の状態がさらされることはない
  ;; `alter-var-root`を通常の業務プログラム中で使うことは稀で、defの値の変更は
  ;; 一般的に推奨されない。
  ;; alter-var-rootは値（ここではsystem)を変更する関数を第二引数にとる。ここでは
  ;; SystemMapをセットしたいので、任意の引数をとり、常に`(systems/base-system)`の結果を
  ;; 返す`constantly`関数を使用している。
  (alter-var-root #'system
                  (constantly (systems/base-system))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system (fn [s] (when s (component/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  ;; refreshした段階で`'user/go`の定義も消去されているので、
  ;; 予めrefresh関数に渡しておき、終了後呼び出してbootstrapしてもらう
  (refresh :after 'user/go))

;(go)
