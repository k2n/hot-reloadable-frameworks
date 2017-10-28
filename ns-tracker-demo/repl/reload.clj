(ns reload)

(use 'ns-tracker.core)

(require 'ns-tracker-demo.foo :reload)

(require 'ns-tracker-demo.bar :reload)

(def modified-namespaces 
    (ns-tracker ["src" "test"]))

(doseq [ns-sym (modified-namespaces)]
  (println ns-sym)
  (require ns-sym :reload))
