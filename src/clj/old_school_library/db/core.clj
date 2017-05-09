(ns old-school-library.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [monger.json]
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
  (try
    (mc/find-one-as-map db "authors" {:key key})
    (catch Exception e (str ";; Caught exception: " (.getMessage e)))))

(defn get-work [author-key titlenum]
  (let [author-doc (mc/find-one-as-map db "authors" {:key author-key})]
    (let [name (:author author-doc)
          title (get-in author-doc [:titles (Integer/parseInt titlenum)])]
      (mc/find-one-as-map db "works" {:author name :title title}))))  
