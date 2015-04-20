(ns diplomacy.core-test
  (:require [clojure.test :refer :all]
            [diplomacy.core :refer :all]
            [diplomacy.board :refer :all] :reload))

;; Use these tests too
;; http://web.inter.nl.net/users/L.B.Kruijswijk/

(deftest core

  (testing "Primitive geography logic"

    (testing "Adjacent land"
      (is (adjacent-land? 'Livonia 'Moscow))
      (is (adjacent-land? 'Moscow 'Livonia))
      (is (not (adjacent-land? 'Moscow 'Finland))))

    (testing "Adjacent waters"
      (is (adjacent-water? 'Gulf-of-Bothnia 'Baltic-Sea))
      (is (not (adjacent-water? 'Gulf-of-Bothnia 'Barents-Sea)))
      (is (not (adjacent-water? 'Saint-Petersburg 'Barents-Sea))))

    (testing "Adjacent coasts"
      ;; no special north/south coast case
      (is (adjacent-coast? 'Finland 'Sweden))
      ;; these coasts are adjacent...
      (is (adjacent-coast? 'Finland (with-meta 'Saint-Petersburg {:coast :south})))
      ;; but these *coasts* are not adjacent...
      (is (not (adjacent-coast? 'Finland (with-meta 'Saint-Petersburg {:coast :north}))))
      ;; this boundary does not exist (it is ambiguous)
      (is (not (adjacent-coast? 'Finland 'Saint-Petersburg))) ; this boundary does not exist!
      ;; although adjacent...
      (is (not (adjacent-coast? 'Livonia 'Moscow)))
      ;; although they run along the same body of water...
      (is (not (adjacent-coast? 'Livonia 'Finland)))
      ;; these adjacent territories don't share a coast...
      (is (not (adjacent-coast? 'York 'Liverpool)))

      (testing "Coastal"
        (is (coastal? 'Saint-Petersburg))
        (is (not (coastal? 'Russia))))))

  (testing "Basic Checks"
    (testing "Move to an area that is not a neigh (6.A.1)"
      (let [game {:england {:fleets #{'North-Sea}}}]
        (is (not (valid-move? :england [:fleet 'North-Sea 'Picardy])))))
    (testing "Move army to sea (6.A.2)")
    (testing "Move fleet to land (6.A.3)")
    (testing "Moving to an area that is not a neighbor (6.A.1)"
      (let [game {:england {:armies #{'York}}}]
        (is (valid-move? :england [:army 'York 'Edinburgh] game) "army can move to adjacent land")
        (is (not (valid-move? :england [:army 'York 'North-Sea] game)) "armies can't walk on water")
        (is (not (valid-move? :england [:army 'York 'Clyde] game)) "armies can't skip provinces")))
    (testing "Fleet attack orders"
      (let [game { :england { :fleets #{'York}}}]
        (is (valid-move? :england [:fleet 'York 'Edinburgh] game))
        (is (valid-move? :england [:fleet 'York 'London] game))
        (is (valid-move? :england [:fleet 'York 'North-Sea] game))
        (is (not (valid-move? :england [:fleet 'York 'Liverpool] game)) "not coastally adjacent")
        (is (not (valid-move? :england [:fleet 'York 'English-Channel] game)) "not adjacent")
        (is (not (valid-move? :england [:fleet 'London 'York] game))) "no fleet in london")))

;; Configure fleets
  (testing "Fleet attack orders"
      ; a fleet on the north coast of StP.  cross to the Barents Sea
      (is (valid? '(attack russia :fleets "Saint Petersburg" "Barents Sea")))
      ; a fleet on the south coast of StP. cannot cross to the Barents Sea
      (is (not (valid? '(attack russia :fleets "Saint Petersburg" "Barents Sea"))))
      ; a fleet on the south coast of StP. can move to Livonia
      (is (valid? '(attack russia :fleets "Saint Petersburg" "Livonia")))
      ; a fleet on the north coast of StP. cannot cross to coastal Livonia"
      (is (not (valid? '(attack russia :fleets "Saint Petersburg", "Livonia"))))))
