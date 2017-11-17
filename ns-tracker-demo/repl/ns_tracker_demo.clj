;; gorilla-repl.fileformat = 1

;; **
;;; # ns-tracker デモ
;; **

;; @@
(ns user
  (:require [me.raynes.conch :refer [programs]]
            [ns-tracker.core :refer :all]
            [ns-tracker-demo
             [foo :as foo]
             [bar :as bar]
             [baz :as baz]
             [quux :as quux]]
            [taoensso.timbre :as log]))
;; @@

;; **
;;; まずはclojure標準の `require :reload` の振る舞いを見てみましょう。まずfoo/fooの出力を見ます。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; defonceで定義されたタイムスタンプを確認します。
;; **

;; @@
(foo/current-time)
;; @@

;; **
;;; 次に、エディタでfoo/fooを書き換えたあと、再実行してみます。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; 結果は変わっていませんね。なのでrequire :reloadで再読込する必要があるのです。
;; **

;; @@
(require 'ns-tracker-demo.foo :reload)
(foo/foo)
;; @@

;; **
;;; それでは、ns-trackerを使ったリロードをみていきます。最初に、監視対象のディレクトリを定義します。
;; **

;; @@
(def modified-namespaces 
   (ns-tracker ["src" "test"]))
;; @@

;; **
;;; 依存関係は `foo<-bar, foo<-baz, (foo,bar,baz)<-quux` となっています。まずbarをtouchします。
;; **

;; @@
(programs ls touch)
(let [bar "src/ns_tracker_demo/bar.clj"]
    (touch bar)
  (ls "-l" bar))
;; @@

;; **
;;; それから、ns-trackerを使って影響を受ける依存関係をリロードします。
;; **

;; @@
(doseq [ns-sym (modified-namespaces)]
   (log/info ns-sym)
   (require ns-sym :reload))
;; @@

;; **
;;; fooを更新すれば、foo自身に加えて、bar, baz, quuxがリロードされるはずです。
;; **

;; @@
(programs ls touch)
(let [foo "src/ns_tracker_demo/foo.clj"]
    (touch foo)
  (ls "-l" foo))
;; @@

;; @@
リロードします。
;; @@

;; @@
(doseq [ns-sym (modified-namespaces)]
   (log/info ns-sym)
   (require ns-sym :reload))
;; @@

;; **
;;; 次に関数fooの定義を削除してからリロードします。
;; **

;; @@
(doseq [ns-sym (modified-namespaces)]
   (log/info ns-sym)
   (require ns-sym :reload))
;; @@

;; **
;;; もう一度fooを呼んでみます。残念ながら定義はまだ残っています。
;; **

;; @@
(foo/foo)
;; @@

;; **
;;; もう一度タイムスタンプを確認します。defonceで定義されているので、値は最初と変わらないはずです。
;; **

;; @@
(foo/current-time)
;; @@

;; **
;;; 以上でns-trackerのデモは終了です。
;; **
