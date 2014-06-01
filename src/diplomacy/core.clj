(ns diplomacy.core)


(def lands #{"Finland"
             "Saint Petersburg"
             "Livonia"
             "Moscow"
             "Warsaw"
             "Ukraine"
             "Stevastapol"
             "Sweden"
             "Norway" })


(def waters #{"Black Sea"
              "Gulf of Bothnia"
              "Barents Sea"
              "Baltic Sea"})


(def boundaries #{ #{"Moscow" "Saint Petersburg"}
                   #{"Moscow" "Livonia"}
                   #{"Moscow" "Warsaw"}
                   #{"Moscow" "Ukraine"}
                   #{"Moscow" "Stevastapol"}
                   ; StP. is coastally adjacent along the north...
                   ^{"Saint Petersburg" :north} #{"Saint Petersburg" "Barents Sea"}
                   ^{"Saint Petersburg" :north} #{"Saint Petersburg" "Norway"}
                   ; StP. is coastally adjacent along the south...
                   ^{"Saint Petersburg" :south} #{"Saint Petersburg" "Gulf of Bothnia"}
                   ^{"Saint Petersburg" :south} #{"Saint Petersburg" "Finland"}
                   ^{"Saint Petersburg" :south} #{"Saint Petersburg" "Livonia"}
                   #{"Warsaw" "Livonia"}
                   #{"Warsaw" "Ukraine"}
                   #{"Gulf of Bothnia" "Finland"}
                   #{"Gulf of Bothnia" "Livonia"}
                   #{"Gulf of Bothnia" "Baltic Sea"}
                   #{"Ukraine" "Stevastapol"}
                   #{"Stevastapol" "Black Sea"}})

(defn adjacent? [p1 p2]
  (contains? boundaries #{p1 p2}))
(adjacent? "Livonia" "Moscow")
(adjacent? "Moscow" "Livonia")


(defn adjacent-land? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} lands)))
(adjacent-land? "Moscow" "Livonia")


(defn adjacent-water? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} waters)))


(defn adjacent-coast? [p1 p2 & coast]
  (let [boundary (get boundaries #{p1 p2})]
    ; this makes assumptions: both coast and boundary
    ; have a single key/val
    (= (first coast) (meta boundary))))
(adjacent-coast? "Saint Petersburg" "Finland" {"Saint Petersburg" :south})
(adjacent-coast? "Saint Petersburg" "Finland" {"Saint Petersburg" :north})


(defn coastal? [province]
  (some waters (adjacent province)))

(coastal? "Stevastapol")
(coastal? "Moscow")


; Each power controls armies, fleets, and cities.
(def russia
  { :armies #{"Moscow" "Warsaw"}
    :fleets ^{"Saint Petersburg" :south} #{"Saint Petersburg" "Stevastapol"}
    :cities #{"Moscow" "Saint Petersburg" "Warsaw" "Stevastapol"} })
(def turkey
  { :armies #{"Constantinople" "Smyrna"}
    :fleets #{"Ankara"}
    :cities #{"Ankara" "Constantinople" "Smyrna"}})

; A power may not have more units than cities.
(defn balance [power]
  (- (count (:cities russia))
     (+ (count (:armies russia)) (count (:fleets russia)))))


