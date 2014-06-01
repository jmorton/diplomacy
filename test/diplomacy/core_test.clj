(ns diplomacy.core-test
  (:require [clojure.test :refer :all]
            [diplomacy.core :refer :all] :reload))

; These tests make assumptions on data in diplomacy.board

(deftest adjacency
  (testing "Adjacent land"
    (is (adjacent-land? "Livonia" "Moscow"))
    (is (adjacent-land? "Moscow" "Livonia"))
    (is (not (adjacent-land? "Moscow" "Finland"))))
  (testing "Adjacent waters"
    (is (adjacent-water? "Gulf of Bothnia" "Baltic Sea"))
    (is (not (adjacent-water? "Gulf of Bothnia" "Barents Sea")))
    (is (not (adjacent-water? "Saint Petersburg" "Barents Sea"))))
  (testing "Adjacent coasts"
    (is (adjacent-coast? "Finland" "Sweden"))
    (is (adjacent-coast? "Finland" "Saint Petersburg" {"Saint Petersburg" :south}))
    (is (not (adjacent-coast? "Finland" "Saint Petersburg" {"Saint Petersburg" :north})))
    (is (not (adjacent-coast? "Livonia" "Moscow"))))
  (testing "Coastal"
    (is (coastal? "Saint Petersburg"))
    (is (not (coastal? "Russia")))))
