(ns old-school-library.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [mount.core :refer [defstate]]
              [old-school-library.config :refer [env]]))

(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))

(defn create-user [user]
  (mc/insert db "users" user))

(defn update-user [id first-name last-name email]
  (mc/update db "users" {:_id id}
             {$set {:first_name first-name
                    :last_name last-name
                    :email email}}))

(defn get-user [id]
  (mc/find-one-as-map db "users" {:_id id}))

(defn get-author [key]
  ;; (println (str "Getting author: " key))
  (try
    (let [author (mc/find-one-as-map db "authors" {:key key})]
      ;; (println author)
      (update-in author [:_id] #(.toString %)))
    (catch Exception e (str ";; Caught exception: " (.getMessage e)))))

(defn get-work [author titlenum]
  {:title "All's Well That Ends Well"
   :date "c. 1604-5"
   :genre "Comedy Play"
   :text {:characters ["Abe Froman" "Gulliver" "Lancel Lannister"]
          :parts ["Act 1" "Act 2" "Act 3" "Act 4" "Act 5"]}})  
