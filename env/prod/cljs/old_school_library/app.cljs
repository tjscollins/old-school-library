(ns old-school-library.app
  (:require [old-school-library.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
