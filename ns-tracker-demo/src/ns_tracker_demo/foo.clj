;; gorilla-repl.fileformat = 1

;; @@
(ns ns-tracker-demo.foo
  (:import [java.time ZonedDateTime]))

(defn foo [] "foo is called")

(defmacro with-foo [f]
  `(do (println "foo")
       ~f))

(defprotocol IFoo
  (say [this]))

(defrecord FooRecord [voice]
  IFoo
  (say [this] (str "Hi, " (:voice this))))

(defonce now (ZonedDateTime/now))

(defn current-time [] (str now))

;; @@
