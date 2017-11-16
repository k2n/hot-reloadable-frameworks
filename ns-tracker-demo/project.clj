(defproject ns-tracker-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure         "1.8.0"]
                 [com.taoensso/timbre         "4.10.0"]
                 [ns-tracker                  "0.3.1"]]
  :source-paths ["src" "repl"]
  :profiles {:dev {:dependencies [[me.raynes/conch "0.8.0"]] 
                   :plugins [[lein-gorilla "0.4.0"]]}})
