(ns ns-tracker-demo.baz
  (:require [ns-tracker-demo.foo :as foo]))

(defn baz [x y]
  (foo/with-foo (* x y)))

(def my-foo-record (foo/->FooRecord "say baz"))

