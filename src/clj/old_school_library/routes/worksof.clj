(ns old-school-library.routes.worksof
  (:require [old-school-library.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]
            [clojure.java.io :as io]))

(defn worksof-handler [request]
  (response  {:author "William Shakespeare"
              :titles ["All's Well That Ends Well"
                       "Winter's Tale"]
              :bio {:birth "1564-04-23"
                    :death "1616-04-23"
                    :country "England"
                    }}))

(defroutes worksof-routes
  (GET "/worksof/:author" [author]
       (wrap-json-response worksof-handler)))
