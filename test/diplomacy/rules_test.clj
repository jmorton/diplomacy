(ns diplomacy.rules-test
  (:require [clojure.test :refer :all]
            [diplomacy.rules :refer :all]
            :reload))

(deftest rules
  (testing "neighborness"
    (is (some #{'Smyrna} (neighbors 'Constantinople europe)))))
