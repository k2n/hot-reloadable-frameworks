(ns reload
  (:require [clojure.tools.namespace.repl :refer [refresh refresh-all]]
            [namespace-demo.foo :as foo]))

(comment
  (foo/foo)
 ;; eval foo/now
 ;;foo/fooを編集する
  (refresh-all)
 ;; eval foo/now again 
  (foo/foo))
