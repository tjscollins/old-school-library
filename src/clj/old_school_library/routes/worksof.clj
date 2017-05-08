(ns old-school-library.routes.worksof
  (:require [old-school-library.layout :as layout]
            [old-school-library.db.core :as db]
            [clojure.data.json :as json]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]))

(defn worksof-handler [request]
  ;; (println db/db)
  (let [author (-> request :route-params :author)]
    (response {:body (db/get-author author)})))
  

(defroutes worksof-routes
  (GET "/worksof/:author" [author]
       (wrap-json-response worksof-handler)))
