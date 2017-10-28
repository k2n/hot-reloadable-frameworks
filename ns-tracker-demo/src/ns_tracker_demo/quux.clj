(ns ns-tracker-demo.quux
  (:require [ns-tracker-demo
               [foo :as foo] 
               [bar :as bar] 
               [baz :as baz]]))

(defn say []
  (for [x [bar/my-foo-record baz/my-foo-record]]
    (foo/say x)))
