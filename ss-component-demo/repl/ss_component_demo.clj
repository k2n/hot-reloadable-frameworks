;; gorilla-repl.fileformat = 1

;; **
;;; ## Stuart Sierra's Component デモ
;; **

;; @@
(ns user
  (:require [clojure.tools.namespace.repl :refer [set-refresh-dirs]]
            [ss-component-demo.systems :as systems]
            [ss-component-demo.components.app :as app]))

;; 本ファイルの修正が影響を及ぼさないようにsource pathsを再定義
(set-refresh-dirs "src" "resources")

;; @@

;; **
;;; - REPLで対話的に使用するコマンドが `user` に定義されている。
;;; - systems.cljがコンポーネントの組み立てを行い、userにシステムマップを渡す。
;;; - confコンポーネントはresources/app-conf.ednを読み込む。
;;; - dbコンポーネントはJDBC URLをconfコンポーネントから受取り、JDBCコネクションを確立する。（ここではダミーのObjectInputStream)
;;; - appコンポーネントはconfとdbコンポーネントを受取り、アプリの機能を実装する。実際はこれを更に細分化することになる。
;; **

;; @@
(go)
;; @@

;; @@
(reset)
;; @@

;; **
;;; Ring Appであれば、handlerにシステムマップをclose overして、compojure routeからアクセスできるようにすることが可能。ここではスタンドアローンアプリなので、systemからエントリーコンポーネントであるappに直接アクセス。
;; **

;; @@
(app/show (:app system))
;; @@
