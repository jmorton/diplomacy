(ns diplomacy.board
  (:require [clojure.set :refer [union]]))

;; a game consists of powers with armies, fleets, and cities.
(def game {:turn    {:year 1901 :season 'spring :phase 'order}
           :russia  {:armies #{:Moscow :Warsaw}
                     :fleets #{:Sevastapol :Saint-Petersburg}
                     :cities #{:Moscow :Warsaw :Sevastapol :Saint-Petersburg}
                     :coasts #{}
                     :orders #{}
                     :ousted #{}}
           :austria {:armies #{ :Vienna :Budapest }
                     :fleets #{ :Trieste }
                     :cities #{ :Vienna :Budapest :Trieste }
                     :ousted #{}}})


(def spring-phases '[talk write resolve retreat])

(def fall-phases (conj spring-phases 'build))

(def austria (set [:Vienna :Budapest :Trieste :Tyrolia :Bohemia :Galicia]))
(def england (set [:London :Liverpool :Edinburgh :Wales :York :Clyde]))
(def france  (set [:Marseilles :Breast :Paris :Burgundy :Gascony :Picardy]))
(def germany (set [:Berlin :Kiel :Munich :Ruhr :Prussia :Silesia]))
(def italy   (set [:Roma :Napoli :Venezia :Piemonte :Tuscany :Apulia]))
(def russia  (set [:Moscow :Saint-Petersburg :Warsaw :Stevastapol :Ukraine :Livonia :Finland]))
(def turkey  (set [:Constantinople :Ankara :Smyrna :Armenia :Syria]))
(def neutral (set [:Sweden :Norway :Denmark :Holland :Belgium
                   :Rumania :Bulgaria :Serbia :Albania :Greece
                   :Tunisia :Spain :Portugal :North-Africa]))
(def ignore  (set [:Ireland :Switzerland :Iceland]))

(def land (union austria england france germany italy russia turkey neutral))

(def city (set [:Moscow :Warsaw :Saint-Petersburg :Stevastapol
                :Sweden :Norway :Denmark :Holland :Belgium
                :Vienna :Budapest :Trieste
                :London :Liverpool :Edinburgh
                :Paris :Brest :Marseilles
                :Portugal :Spain :Tunisia
                :Roma :Napoli :Venezia
                :Serbia :Rumania :Bulgaria
                :Ankara :Smyrna :Constantinople]))

(def water (set [:Black-Sea
                 :Gulf-of-Bothnia
                 :Barents-Sea
                 :Baltic-Sea
                 :Norwegian-Sea
                 :North-Sea
                 :Skagerrak
                 :English-Channel
                 :Irish-Sea
                 :North-Atlantic
                 :Mid-Atlantic
                 :Gulf-of-Lyon
                 :West-Mediterranean
                 :Tyrhennian-Sea
                 :Ionian-Sea
                 :Adriatic-Sea
                 :Aegean-Sea
                 :East-Mediterranean]))

(def boundaries #{#{:Moscow :Saint-Petersburg}
                  #{:Moscow :Livonia}
                  #{:Moscow :Warsaw}
                  #{:Moscow :Ukraine}
                  #{:Moscow :Stevastapol}
                  #{:Saint-Petersburg :Barents-Sea} ;; north
                  #{:Saint-Petersburg :Norway} ;; north
                  #{:Saint-Petersburg :Gulf-of-Bothnia} ;; south
                  #{:Saint-Petersburg :Finland} ;; south
                  #{:Saint-Petersburg :Livonia} ;; south
                  #{:Warsaw :Livonia}
                  #{:Warsaw :Ukraine}
                  #{:Gulf-of-Bothnia :Finland}
                  #{:Gulf-of-Bothnia :Livonia}
                  #{:Gulf-of-Bothnia :Baltic-Sea}
                  #{:Livonia :Baltic-Sea}
                  #{:Ukraine :Stevastapol}
                  #{:Stevastapol :Black-Sea}
                  #{:Sweden :Finland}
                  #{:Sweden :Norway}
                  #{:Sweden :Gulf-of-Bothnia}
                  #{:Norway :Norwegian-Sea}
                  #{:Norway :Barents-Sea}
                  #{:London :York}
                  #{:London :Wales}
                  #{:London :English-Channel}
                  #{:London :North-Sea}
                  #{:York :Wales}
                  #{:York :Liverpool} ;; land-only
                  #{:York :Edinburgh}
                  #{:York :North-Sea}
                  #{:Liverpool :Wales}
                  #{:Liverpool :Clyde}
                  #{:Edinburgh :North-Sea}
                  #{:Liverpool :Irish-Sea}
                  #{:Liverpool :North-Atlantic}
                  #{:Brest :Paris}
                  #{:Brest :Gascony}
                  #{:Brest :Picardy}
                  #{:Brest :English-Channel}
                  #{:Brest :Mid-Atlantic}})
