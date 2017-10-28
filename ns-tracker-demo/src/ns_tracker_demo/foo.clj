(ns ns-tracker-demo.foo)

(defn foo [] "foo is called")

(defmacro with-foo [f]
  `(do (println "foo")
      ~f))

(defprotocol IFoo
  (say [this]))

(defrecord FooRecord [voice]
  IFoo 
  (say [this] (str "Hi, " (:voice this))))
