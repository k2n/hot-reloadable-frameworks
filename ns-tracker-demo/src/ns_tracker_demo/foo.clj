(ns ns-tracker-demo.foo)

(defn foo [] "FOO IS CALLED")

(defmacro with-foo [f]
  `(do (println "foo")
       ~f))

(defprotocol IFoo
  (say [this]))

(defrecord FooRecord [voice]
  IFoo
  (say [this] (str "Hi, " (:voice this))))
