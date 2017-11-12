(ns reload
  (:require [ns-tracker.core :refer :all]
            [taoensso.timbre :as log]))

(def modified-namespaces 
   (ns-tracker ["src" "test"]))

(comment
 (require 'ns-tracker-demo.foo :reload)

 (require 'ns-tracker-demo.bar :reload)

 (doseq [ns-sym (modified-namespaces)]
   (log/info ns-sym)
   (require ns-sym :reload)))
