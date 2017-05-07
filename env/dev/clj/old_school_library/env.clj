(ns old-school-library.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [old-school-library.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[old-school-library started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[old-school-library has shut down successfully]=-"))
   :middleware wrap-dev})
