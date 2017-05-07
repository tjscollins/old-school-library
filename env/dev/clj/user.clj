(ns user
  (:require [mount.core :as mount]
            [old-school-library.figwheel :refer [start-fw stop-fw cljs]]
            old-school-library.core))

(defn start []
  (mount/start-without #'old-school-library.core/repl-server))

(defn stop []
  (mount/stop-except #'old-school-library.core/repl-server))

(defn restart []
  (stop)
  (start))


