;; gorilla-repl.fileformat = 1

;; @@
(ns namespace-demo.foo
  (:import [java.time ZonedDateTime]))

(defn foo []
  "returned from function foo")

(defn call-from-bar []
  "hi, bar!")

(defonce now (ZonedDateTime/now))

(defn current-time [] (str now))
;; @@
