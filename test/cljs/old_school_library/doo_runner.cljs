(ns old-school-library.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [old-school-library.core-test]))

(doo-tests 'old-school-library.core-test)

