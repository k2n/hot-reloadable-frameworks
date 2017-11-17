;; gorilla-repl.fileformat = 1

;; **
;;; ##mountデモ
;; **

;; @@
(ns user
  (:require [mount-demo.app :as app]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; まずdefstateで定義されたvarを参照している(app/show-app)を呼んでみる。
;; **

;; @@
(app/show-app)
;; @@
;; ->
;;; 17-11-17 05:27:33 k2n-mbp13.local INFO [mount-demo.app:12] - conf: {:db {:url &quot;jdbc:postgresql://localhost:5432/mydb&quot;, :username &quot;postgres&quot;, :password &quot;password123&quot;}}
;;; 17-11-17 05:27:33 k2n-mbp13.local INFO [mount-demo.app:13] - connection:  java.io.BufferedOutputStream@c65e13a
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; defstateのタイプはmount.core.DerefableStateで、(mount.core/start)を呼ぶことではじめて実体への参照となる。
;; **

;; @@
(app/start-app)
;; @@
;; ->
;;; 17-11-17 05:26:12 k2n-mbp13.local INFO [mount-demo.db:15] - opening a connection...
;;; 17-11-17 05:26:12 k2n-mbp13.local INFO [mount-demo.db:11] - DB connection info: jdbc:postgresql://localhost:5432/mydb postgres password123
;;; 
;; <-
;; =>
;;; {"type":"list-like","open":"<span class='clj-map'>{</span>","close":"<span class='clj-map'>}</span>","separator":", ","items":[{"type":"list-like","open":"","close":"","separator":" ","items":[{"type":"html","content":"<span class='clj-keyword'>:started</span>","value":":started"},{"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-string'>&quot;#&#x27;mount-demo.conf/conf&quot;</span>","value":"\"#'mount-demo.conf/conf\""},{"type":"html","content":"<span class='clj-string'>&quot;#&#x27;mount-demo.db/conn&quot;</span>","value":"\"#'mount-demo.db/conn\""}],"value":"[\"#'mount-demo.conf/conf\" \"#'mount-demo.db/conn\"]"}],"value":"[:started [\"#'mount-demo.conf/conf\" \"#'mount-demo.db/conn\"]]"}],"value":"{:started [\"#'mount-demo.conf/conf\" \"#'mount-demo.db/conn\"]}"}
;; <=

;; **
;;; 今度は実体が保持されている。
;; **

;; @@
(app/show-app)
;; @@
;; ->
;;; 17-11-17 05:26:16 k2n-mbp13.local INFO [mount-demo.app:12] - conf: {:db {:url &quot;jdbc:postgresql://localhost:5432/mydb&quot;, :username &quot;postgres&quot;, :password &quot;password123&quot;}}
;;; 17-11-17 05:26:16 k2n-mbp13.local INFO [mount-demo.app:13] - connection:  java.io.BufferedOutputStream@c65e13a
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; ns-trackerを使うことで、明示的にstop/startを呼ばなくても、更新されたnamespaceに定義されているstateとその依存関係のみをリスタートしてくれる。例として、confのdb URLを変更してみると、confと、confを使っているconnがリスタートされる。
;; **

;; @@
(reload)
;; @@
;; ->
;;; &quot;&gt;&gt; starting.. #&#x27;mount-demo.conf/conf (namespace was recompiled)&quot;
;;; &quot;&lt;&lt; stopping.. #&#x27;mount-demo.db/conn (namespace was recompiled)&quot;
;;; 17-11-17 05:18:57 k2n-mbp13.local INFO [mount-demo.db:21] - connection closed.
;;; &quot;&gt;&gt; starting.. #&#x27;mount-demo.db/conn (namespace was recompiled)&quot;
;;; 17-11-17 05:18:57 k2n-mbp13.local INFO [mount-demo.db:15] - opening a connection...
;;; 17-11-17 05:18:57 k2n-mbp13.local INFO [mount-demo.db:11] - DB connection info: jdbc:postgresql://localhost:5432/mydb postgres password123
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@

;; @@
