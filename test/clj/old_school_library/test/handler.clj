(ns old-school-library.test.handler
  (:require [clojure.test :refer :all]
            [clojure.data.json :as json]
            [ring.mock.request :refer :all]
            [old-school-library.handler :refer :all]))

(deftest test-routes
  "Verify that all routes function as expected"
  (testing "GET /"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "GET /worksof/:author"
    (let [response ((app) (request :get "/worksof/shakespeare"))]
      (is (= 200 (:status response)))
      (let [data (json/read-str (:body response))]
        ;; (println data)
        (is (= "William Shakespeare" (get-in data ["author"])))
        (is (= "All's Well That Ends Well" (first (get-in data ["titles"]))))
        (is (= "Winter's Tale" (last (get-in data ["titles"]))))
        (is (= "1564-04-23" (get-in data ["bio" "birth"])))
        (is (= "1616-04-23" (get-in data ["bio" "death"])))
        (is (= "England" (get-in data ["bio" "country"]))))))

  (testing "invalid routes"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))
