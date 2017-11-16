;; gorilla-repl.fileformat = 1

;; **
;;; # ns-tracker デモ
;; **

;; @@
(ns ns-tracker-demo.gorilla
  (:require [me.raynes.conch :refer [programs with-programs]]
            [ns-tracker.core :refer :all]
            [ns-tracker-demo
             [foo :as foo]
             [bar :as bar]
             [baz :as baz]
             [quux :as quux]]
            [taoensso.timbre :as log]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; まずはclojure標準の `require :reload` の振る舞いを見てみましょう。まずfoo/fooの出力を見ます。
;; **

;; @@
(foo/foo)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;foo is called&quot;</span>","value":"\"foo is called\""}
;; <=

;; **
;;; 次に、エディタでfoo/fooを書き換えたあと、再実行してみます。
;; **

;; @@
(foo/foo)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;foo is called&quot;</span>","value":"\"foo is called\""}
;; <=

;; **
;;; 結果は変わっていませんね。なのでrequire :reloadで再読込する必要があるのです。
;; **

;; @@
(require 'ns-tracker-demo.foo :reload)
(foo/foo)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;FOO IS CALLED&quot;</span>","value":"\"FOO IS CALLED\""}
;; <=

;; **
;;; それでは、ns-trackerを使ったリロードをみていきます。最初に、監視対象のディレクトリを定義します。
;; **

;; @@
(def modified-namespaces 
   (ns-tracker ["src" "test"]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;ns-tracker-demo.gorilla/modified-namespaces</span>","value":"#'ns-tracker-demo.gorilla/modified-namespaces"}
;; <=

;; **
;;; 依存関係は `foo<-bar, foo<-baz, (foo,bar,baz)<-quux` となっています。まずbarをtouchします。
;; **

;; @@
(programs ls touch)
(let [baz "src/ns_tracker_demo/bar.clj"]
    (touch baz)
  (ls "-l" baz))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-string'>&quot;-rw-r--r--  1 kenji  staff  193 Nov 15 21:57 src/ns_tracker_demo/bar.clj\\n&quot;</span>","value":"\"-rw-r--r--  1 kenji  staff  193 Nov 15 21:57 src/ns_tracker_demo/bar.clj\\n\""}
;; <=

;; **
;;; それから、ns-trackerを使って影響を受ける依存関係をリロードします。
;; **

;; @@
(doseq [ns-sym (modified-namespaces)]
   (log/info ns-sym)
   (require ns-sym :reload))
;; @@
;; ->
;;; 17-11-15 12:57:18 k2n-mbp13.local INFO [ns-tracker-demo.gorilla:2] - ns-tracker-demo.bar
;;; 17-11-15 12:57:18 k2n-mbp13.local INFO [ns-tracker-demo.gorilla:2] - ns-tracker-demo.quux
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=
