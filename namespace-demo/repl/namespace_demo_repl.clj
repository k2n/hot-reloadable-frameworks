;; gorilla-repl.fileformat = 1

;; **
;;; ##Namespaceデモ
;; **

;; @@
(ns user
  (:require [me.raynes.conch :refer [programs]]
            [clojure.java.io :as io]
            [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]
            [namespace-demo.foo :as foo]
            [namespace-demo.bar :as bar]
            [taoensso.timbre :as log]))

;; 本ファイルの修正が影響を及ぼさないようにsource pathsを再定義
(set-refresh-dirs "src" "resources")

;; @@

;; **
;;; foo内でdefonceで定義されたタイムスタンプを確認します。本来defonceはJVMのライフサイクルの間同じ値が保持されているはずです。
;; **

;; @@
(foo/current-time)
;; @@

;; **
;;; ファイル間の依存関係はfoo<-barです。関数foo/fooを編集してみます。
;; **

;; **
;;; foo/fooを呼び出しても、返り値は古いままであることを確認します。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; tools.namespaceを使って、refreshします。既存の定義は全て削除されてから再ロードされます。
;; **

;; @@
(refresh)
;; @@

;; **
;;; 関数foo/fooが定義されています。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; 関数foo/fooをコメントアウトし、refreshします。
;; **

;; @@
(refresh)
;; @@

;; **
;;; 関数foo/fooをもう一度呼ぶと、予想通りUnbound errorになります。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; defonceで定義されたタイムスタンプも更新されています。
;; **

;; @@
(foo/current-time)
;; @@

;; **
;;; [作業メモ:foo/fooの修正をロールバックしておくこと]
;; **
