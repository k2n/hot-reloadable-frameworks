(ns ss-component-demo.systems
  (:require [com.stuartsierra.component :as component]
            [ss-component-demo.components
             [app :refer [new-app]]
             [conf :refer [new-conf]]
             [db :refer [new-db]]]))

(defn base-system []
  (component/system-map
   :conf (new-conf)
   :db  (component/using (new-db)
                         [:conf])
   :app (component/using (new-app)
                         [:conf :db])))
