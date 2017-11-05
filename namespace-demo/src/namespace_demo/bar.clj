(ns namespace-demo.bar
  (:require [namespace-demo.foo :as foo]))

(defn bar []
  (foo/foo))
