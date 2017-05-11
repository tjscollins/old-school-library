(ns old-school-library.test.dbseed
  (:require [clojure.test :refer :all]
            [clojure.data.json :as json]
            [old-school-library.db.core :as db]
            [old-school-library.db.validators :as v]
            [validateur.validation :refer [valid? invalid?]]
            [monger.core :as mg]
            [monger.collection :as mc]
            [monger.operators :refer :all]))

(defn seed
  "Read seed data from local json"
  []
  (let [authors (json/read-str (slurp "test/clj/old_school_library/test/authors.json")
                               :key-fn keyword)
        works (json/read-str (slurp "test/clj/old_school_library/test/works.json")
                             :key-fn keyword)]
    [authors works]))

(defn plant-seed
  "Seed database with data from local json"
  []
  (let [[authors works] (seed)]
    (mc/remove db/db "authors")
    (mc/remove db/db "works")
    (mc/insert-batch db/db "authors" authors)
    (mc/insert-batch db/db "works" works)))

(deftest seed-test
  "test db seed function"
  (testing "dbseed/seed"
    ;; (spit "seed.txt" (seed))
    (let [[authors works] (seed)]
      (is (every?
           #(valid? v/authors-validator %)
           authors))
      (is (every?
           #(valid? v/works-validator %)
           works)))))
