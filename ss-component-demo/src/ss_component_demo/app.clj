(ns ss-component-demo.app
  (:require [com.stuartsierra.component :as component]
            [ss-component-demo.components
             [conf :refer [new-conf]]
             [db :refer [new-db]]]))

(defn base-system []
  (component/system-map
   :conf (new-conf)
   :db  (component/using (new-db)
                         [:conf])))
