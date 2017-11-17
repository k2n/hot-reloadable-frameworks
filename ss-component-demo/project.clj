(defproject ss-component-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure         "1.8.0"]
                 [com.taoensso/timbre         "4.10.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [com.stuartsierra/component  "0.3.2"]]
  :profiles {:dev {:source-paths ["dev"]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.21.1"]
                             [lein-gorilla                      "0.4.0"]]}})
