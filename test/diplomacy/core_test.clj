(ns diplomacy.core-test
  (:require [clojure.test :refer :all]
            [diplomacy.core :refer :all] :reload))

; These tests make assumptions on data in diplomacy.board

(deftest core

  (testing "Adjacent land"
    (is (adjacent-land? 'Livonia 'Moscow))
    (is (adjacent-land? 'Moscow 'Livonia))
    (is (not (adjacent-land? 'Moscow 'Finland))))

  (testing "Adjacent waters"
    (is (adjacent-water? 'Gulf-of-Bothnia 'Baltic-Sea))
    (is (not (adjacent-water? 'Gulf-of-Bothnia 'Barents-Sea)))
    (is (not (adjacent-water? 'Saint-Petersburg 'Barents-Sea))))

  (testing "Adjacent coasts"
    (is (adjacent-coast? 'Finland 'Sweden))
    (is (adjacent-coast? 'Finland (with-meta 'Saint-Petersburg {:coast :south})))
    (is (not (adjacent-coast? 'Finland (with-meta 'Saint-Petersburg {:coast :north}))))
    (is (not (adjacent-coast? 'Finland 'Saint-Petersburg))) ; this boundary does not exist!
    (is (not (adjacent-coast? 'Livonia 'Moscow)))
    (is (not (adjacent-coast? 'Livonia 'Finland)))

  (testing "Coastal"
    (is (coastal? 'Saint-Petersburg))
    (is (not (coastal? 'Russia))))))

;;   (testing "Army attack orders"
;;     (let [russia { :armies [ "Livonia" "Moscow" "Ukraine" ]
;;                    :fleets [ "Baltic Sea" ]}
;;           lands #{ "Livonia" "Moscow" "Ukraine" }
;;           waters #{"Baltic Sea"}
;;           boundaries #{ #{"Livonia" "Baltic Sea"}
;;                         #{"Livonia" "Moscow"}
;;                         #{"Livonia" "Ukraine"}}]
;;       ; yes, armies can move to land
;;       (is (valid? '(attack russia :armies "Livonia" "Moscow")))
;;       ; units most move to an adjacent province
;;       (is (not (valid? '(attack russia :armies "Livonia" "Ukraine"))) )
;;       ; armies cannot walk on water
;;       (is (not (valid? '(attack russia :armies "Livonia" "Baltic Sea"))))))
;;   ;; Configure fleets
;;   (testing "Fleet attack orders"
;;       ; a fleet on the north coast of StP.  cross to the Barents Sea
;;       (is (valid? '(attack russia :fleets "Saint Petersburg" "Barents Sea")))
;;       ; a fleet on the south coast of StP. cannot cross to the Barents Sea
;;       (is (not (valid? '(attack russia :fleets "Saint Petersburg" "Barents Sea"))))
;;       ; a fleet on the south coast of StP. can move to Livonia
;;       (is (valid? '(attack russia :fleets "Saint Petersburg" "Livonia")))
;;       ; a fleet on the north coast of StP. cannot cross to coastal Livonia"
;;       (is (not (valid? '(attack russia :fleets "Saint Petersburg", "Livonia"))))



; attack
; convoy
; support
