(ns diplomacy.core-test
  (:require [clojure.test :refer :all]
            [diplomacy.core :refer :all]
            [diplomacy.board :refer :all]
            :reload))

;; Use these tests too
;; http://web.inter.nl.net/users/L.B.Kruijswijk/

(deftest core

  (testing "Primitive geography logic"

    (testing "Adjacency"
      (is (adjacent? 'Livonia 'Moscow))
      (is (adjacent? 'Moscow 'Livonia))
      (is (adjacent? 'Livonia 'Baltic-Sea))
      (is (adjacent? 'Baltic-Sea 'Livonia))
      (is (not (adjacent? 'Gulf-of-Bothnia 'Moscow))))

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
    (testing "Move to an area that is not a neighbor (6.A.1)"
      (let [game {:england {:fleets #{'North-Sea}}}]
        (is (not (valid-move? :england [:fleet 'North-Sea 'Picardy] game)))))
    (testing "Move army to sea (6.A.2)"
      (let [game {:england {:armies #{'York}}}]
        (is (not (valid-move? :england [:army 'York 'North-Sea] game)) "armies can't walk on water"))
    (testing "Move fleet to land (6.A.3)"
      (let [game {:england {:fleets #{'Brest}}}]
        (is (not (valid-move? :england [:fleet 'Brest 'Paris] game)) "fleets can't walk on land")))
    (testing "Moving to an area that is not a neighbor (6.A.1)"
      (let [game {:england {:armies #{'York}}}]
        (is (valid-move? :england [:army 'York 'Edinburgh] game) "army can move to adjacent land")
        (is (not (valid-move? :england [:army 'York 'Clyde] game)) "armies can't skip provinces")))
    (testing "Fleets must follow coasts if not on sea (6.A.9)")
    (testing "Fleet attack orders"
      (let [game { :england { :fleets #{'York}}}]
        (is (valid-move? :england [:fleet 'York 'Edinburgh] game))
        (is (valid-move? :england [:fleet 'York 'London] game))
        (is (valid-move? :england [:fleet 'York 'North-Sea] game))
        (is (not (valid-move? :england [:fleet 'York 'Liverpool] game)) "not coastally adjacent")
        (is (not (valid-move? :england [:fleet 'York 'English-Channel] game)) "not adjacent")
        (is (not (valid-move? :england [:fleet 'London 'York] game))) "no fleet in london"))))

  (testing "Fleet attack orders"
    (let [game { :russia { :fleets #{ (with-meta 'Saint-Petersburg {:coast :south})}}}]
      ;; a fleet on the north coast of StP.  cross to the Barents Sea
      (is (valid-move? '(attack russia :fleets "Saint Petersburg" "Barents Sea")))
      ;; a fleet on the south coast of StP. cannot cross to the Barents Sea
      (is (not (valid-move? '(attack russia :fleets "Saint Petersburg" "Barents Sea"))))
      ;; a fleet on the south coast of StP. can move to Livonia
      (is (valid-move? '(attack russia :fleets "Saint Petersburg" "Livonia")))
      ;; a fleet on the north coast of StP. cannot cross to coastal Livonia"
      (is (not (valid-move? '(attack russia :fleets "Saint Petersburg", "Livonia")))))))
