(ns namespace-demo.foo
  (:import [java.time ZonedDateTime]))


(defn foo
  []
  (println  "Foo"))

(defonce now (ZonedDateTime/now))
