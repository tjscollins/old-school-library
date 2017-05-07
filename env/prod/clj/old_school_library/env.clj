(ns old-school-library.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[old-school-library started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[old-school-library has shut down successfully]=-"))
   :middleware identity})
