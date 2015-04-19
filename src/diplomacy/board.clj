(ns diplomacy.board
  (:require [clojure.set :refer [union]]))

;; a game consists of powers with armies, fleets, and cities.
(def game {:turn    {:year 1901 :season 'spring :phase 'order}
           :russia  {:armies (set '[Moscow Warsaw])
                     :fleets (set '[Sevastapol (with-meta 'Saint-Petersburg { :coast :south })])
                     :cities (set '[Moscow 'Warsaw' 'Sevastapol 'Saint-Petersburg])
                     :orders (set '[])
                     :ousted (set '[])}
           :austria {:armies #{ 'Vienna 'Budapest }
                     :fleets #{ 'Trieste }
                     :cities #{ 'Vienna 'Budapest 'Trieste }
                     :ousted #{}}})


(def spring-phases '[talk write resolve retreat])
(def fall-phases (conj spring-phases 'build))

;; game keeps track of the year, season, and game phase:
;; [order] -> [resolve] -> [retreat*] -> [resolve] -> [build*]

;; resolve
;; - move armies/fleets
;; - update cities
;; - create ousted

;; retreat-disband
;; a set of displacements:
;; (power, province, unit-type, retreat-provinces)
;; each power specifies
;; (province, retreat-province)

;; unit-management (after a fall turn)
;; - new-unit (province, unit type)
;; - disband-unit (province)

;; victory detection
;; - 18 or more supply centers controlled


(def austria (set '[^:city Vienna ^:city Budapest ^:city Trieste
                    Tyrolia Bohemia Galicia]))

(def england (set '[^:city London ^:city Liverpool ^:city Edinburgh
                    Wales York Clyde]))

(def france  (set '[^:city Marseilles ^:city Breast ^:city Paris
                    Burgundy Gascony Picardy]))

(def germany (set '[^:city Berlin ^:city Kiel ^:city Munich
                    Ruhr Prussia Silesia]))

(def italy   (set '[^:city Roma ^:city Napoli ^:city Venezia
                    Piemonte Tuscany Apulia]))

(def russia  (set '[^:city Moscow ^:city Saint-Petersburg ^:city Warsaw ^:city Stevastapol
                    Ukraine Livonia Finland]))

(def turkey  (set '[^:city Constantinople ^:city Ankara ^:city Smyrna
                    Armenia Syria]))
  
(def neutral (set '[^:city Sweden ^:city Norway ^:city Denmark ^:city Holland ^:city Belgium
                    ^:city Rumania ^:city Bulgaria ^:city Serbia Albania Greece
                    ^:city Tunisia ^:city Spain ^:city Portugal North-Africa]))

(def ignore  (set '[Ireland Switzerland Iceland]))

(def land-set (union austria england france germany italy russia turkey neutral))

(defn city? [province] )

(def city-set (filter #(:city (meta %)) land-set)

;; could add ^:city to province and filter land-set...
(def city-set (set '[Moscow Warsaw Saint-Petersburg Stevastapol
                     Sweden Norway Denmark Holland Belgium
                     Vienna Budapest Trieste
                     London Liverpool Edinburgh
                     Paris Brest Marseilles
                     Portugal Spain Tunisia
                     Roma Napoli Venezia
                     Serbia Rumania Bulgaria
                     Ankara Smyrna Constantinople]))

(def water-set (set '[Black-Sea
                      Gulf-of-Bothnia
                      Barents-Sea
                      Baltic-Sea
                      Norwegian-Sea
                      North-Sea
                      Skagerrak
                      English-Channel
                      Irish-Sea
                      North-Atlantic
                      Mid-Atlantic
                      Gulf-of-Lyon
                      West-Mediterranean
                      Tyrhennian-Sea
                      Ionian-Sea
                      Adriatic-Sea
                      Aegean-Sea
                      East-Mediterranean]))


(def-province Bulgaria
  :land Constantinople Rumania Serbia Greece
  :east-coast Constantinople Rumania Black-Sea Constantinople
  :north-coast Constantinople Greece Aegean-Sea)

bulgaria is land
balgaria has an east and south coast
bulgaria is connected to serbia
the east coast is connected to rumania, constantinople, and the black sea
the south coast is connected to greece, constantinople, and the aegean sea


(def-province Mid-Atlantic
  Brest
  Gascony
  Portugal
  North-Africa
  English-Channel
  Irish-Sea
  North-Atlantic)

;; (set coll) is used to avoid errors stemming
;; from inadvertant duplicates of boundaries
(def boundaries (set [#{'Moscow 'Saint-Petersburg}
                      #{'Moscow 'Livonia}
                      #{'Moscow 'Warsaw}
                      #{'Moscow 'Ukraine}
                      #{'Moscow 'Stevastapol}
                      ; StP. is coastally adjacent along the north...
                      #{(with-meta 'Saint-Petersburg {:coast :north}) 'Barents-Sea }
                      #{(with-meta 'Saint-Petersburg {:coast :north}) 'Norway }
                      ; StP. is coastally adjacent along the south...
                      #{(with-meta 'Saint-Petersburg {:coast :south}) 'Gulf-of-Bothnia}
                      #{(with-meta 'Saint-Petersburg {:coast :south}) 'Finland}
                      #{(with-meta 'Saint-Petersburg {:coast :south}) 'Livonia}
                      #{'Warsaw 'Livonia}
                      #{'Warsaw 'Ukraine}
                      #{'Gulf-of-Bothnia 'Finland}
                      #{'Gulf-of-Bothnia 'Livonia}
                      #{'Gulf-of-Bothnia 'Baltic-Sea}
                      #{'Livonia 'Baltic-Sea}
                      #{'Ukraine 'Stevastapol}
                      #{'Stevastapol 'Black-Sea}
                      #{'Sweden 'Finland}
                      #{'Sweden 'Norway}
                      #{'Sweden 'Gulf-of-Bothnia}
                      #{'Norway 'Norwegian-Sea}
                      #{'Norway 'Barents-Sea}
                      #{'London 'York}
                      #{'London 'Wales}
                      #{'London 'English-Channel}
                      #{'London 'North-Sea}
                      #{'York 'Wales}
                      ^:land-only #{'York 'Liverpool}
                      #{'York 'Edinburgh}
                      #{'York 'North-Sea}
                      #{'Liverpool 'Wales}
                      #{'Liverpool 'Clyde}
                      #{'Liverpool 'Irish-Sea}
                      #{'Liverpool 'North-Atlantic}]))
