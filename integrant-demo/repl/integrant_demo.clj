;; gorilla-repl.fileformat = 1

;; **
;;; ##Integrant デモ
;; **

;; @@
(ns user)
;; @@

;; **
;;; - userにintegrantをreplから対話的に操作するための関数を定義
;;; - systemはintegrantとの実際の操作を定義
;;; - componentsでコンポーネントの組み立てを定義
;;; - conf, db, appはss-componentと同様
;; **

;; @@
(init)
;; @@

;; **
;;; 
;; **

;; @@
(reset)
;; @@

;; **
;;; コンポーネントの一部だけを再起動したり、一時停止からの復旧時に、初期化とは違う振る舞いを定義することもできる。
;; **

;; @@
(restart)
;; @@
