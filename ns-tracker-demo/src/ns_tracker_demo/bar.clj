(ns ns-tracker-demo.bar
  (:require [ns-tracker-demo.foo :as foo]))

(defn bar [x y]
  (foo/with-foo (+ x y)))

(defn call-foo []
  (foo/foo))

(def my-foo-record (foo/->FooRecord "say bar"))

