(ns old-school-library.test.handler
  (:require [clojure.test :refer :all]
            [clojure.data.json :as json]
            [ring.mock.request :refer :all]
            [old-school-library.handler :refer :all]))

(deftest static-routes
  "Verify that landing page loads"
  (testing "GET /"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response))))))

(deftest api-routes
  "Verify that json api routes respond correctly"
  (testing "GET /worksof/:author"
    (let [response ((app) (request :get "/worksof/shakespeare"))]
      (is (= 200 (:status response)))
      ;; (spit "response.txt" response)
      (let [data (json/read-str (:body response))]
        (is (= "William Shakespeare" (get-in data ["author"])))
        (is (= "All's Well That Ends Well" (first (get-in data ["titles"]))))
        (is (= "Winter's Tale" (last (get-in data ["titles"]))))
        (is (= "1564-04-23" (get-in data ["bio" "birth"])))
        (is (= "1616-04-23" (get-in data ["bio" "death"])))
        (is (= "England" (get-in data ["bio" "country"]))))))

  (testing "GET /worksof/:author/:titlenum"
    (let [response ((app) (request :get "/worksof/shakespeare/0"))]
      (is (= 200 (:status response)))
      (is ((comp not nil?) (:body response)))
      ;; (spit "response.txt" response)
      (let [data (json/read-str (:body response))]
        (is (= "All's Well That Ends Well" (get data "title")))
        (is (= "c. 1604-5" (get data "date")))
        (is (= "Comedy Play" (get data "genre")))
        (is (map? (get data "text")))))))

(deftest invalid-routes
  "Verify that invalid routes correctly return 404"
  (testing "GET /randomly"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))
