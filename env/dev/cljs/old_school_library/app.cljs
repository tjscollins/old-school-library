(ns ^:figwheel-no-load old-school-library.app
  (:require [old-school-library.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
