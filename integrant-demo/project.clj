(defproject integrant-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure         "1.9.0-RC1"]
                 [com.taoensso/timbre         "4.10.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [integrant                   "0.6.1"]]

  :profiles {:dev {:source-paths ["dev"]
                   :plugins [[com.jakemccrary/lein-test-refresh "0.21.1"]]}})
