(ns old-school-library.db.validators
    (:require [validateur.validation :refer :all]))

(def authors-validator
  (validation-set
   (presence-of :key)
   (presence-of :author)
   (presence-of [:bio :birth])
   (presence-of [:bio :death])
   (presence-of [:bio :country])
   (presence-of :titles)
   (validate-by [:author] string? :message "Author must be a string")
   (validate-by [:key]
                #(and (string? %)
                      (= 1
                         (count (clojure.string/split % #"\s"))))
                :message "Key must be a one-word string")
   (validate-by [:bio :birth]
                string?
                :message "bio birth must be a string")
   (validate-by [:bio :death]
                string?
                :message "bio death must be a string")
   (validate-by [:bio :country]
                string?
                :message "bio country must be a string")
   (validate-by [:titles]
                 vector?
                 :message "titles must be a vector")))

(def works-validator
  (validation-set
   (presence-of :title)
   (presence-of :date)
   (presence-of :genre)
   (presence-of :author)
   (presence-of [:text :characters])
   (presence-of [:text :parts])))
