(ns old-school-library.routes.worksof
  (:require [old-school-library.layout :as layout]
            [old-school-library.db.core :as db]
            [compojure.core :refer [defroutes GET]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.util.response :refer [response]]))

(defn numeric? [s]
  (if-let [s (seq s)]
    (let [s (if (= (first s) \-) (next s) s)
          s (drop-while #(Character/isDigit %) s)
          s (if (= (first s) \.) (next s) s)
          s (drop-while #(Character/isDigit %) s)]
      (empty? s))))

(defn worksof-handler [request]
  (let [author (-> request :route-params :author)]
    (response (db/get-author author))))
  
(defn titlenum-handler [request]
  (let [titlenum (-> request :route-params :titlenum)
        author (-> request :route-params :author)]
    (response (db/get-work author titlenum))))

(defroutes worksof-routes
  (GET "/worksof/:author" [author]
       (wrap-json-response worksof-handler))
  (GET "/worksof/:author/:titlenum" [author titlenum]
       (println (str "Request made for work of " author ", work number: " titlenum))
       (if (numeric? titlenum)
         (wrap-json-response titlenum-handler)
         (throw (Exception. (str "/worksof/" author "/" titlenum " should be end with an integer"))))))



