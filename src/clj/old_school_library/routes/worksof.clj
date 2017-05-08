(ns old-school-library.routes.worksof
  (:require [old-school-library.layout :as layout]
            [old-school-library.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]))

(defn worksof-handler [request]
  ;; (println db/db)
  (response  {:author "William Shakespeare"
              :titles ["All's Well That Ends Well"
                       "Winter's Tale"]
              :bio {:birth "1564-04-23"
                    :death "1616-04-23"
                    :country "England"
                    }})
  (println (db/get-author (:author request))))

(defroutes worksof-routes
  (GET "/worksof/:author" [author]
       (wrap-json-response worksof-handler)))
