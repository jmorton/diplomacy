(ns diplomacy.board)

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
              "Baltic Sea"
              "Norwegian Sea"})


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
                   #{"Stevastapol" "Black Sea"}
                   #{"Sweden" "Finland"}
                   #{"Sweden" "Norway"}
                   #{"Sweden" "Gulf of Bothnia"}
                   #{"Norway" "Norwegian Sea"}
                   #{"Norway" "Barents Sea"}})


(def russia
  { :armies #{"Moscow" "Warsaw"}
    :fleets ^{"Saint Petersburg" :south} #{"Saint Petersburg" "Stevastapol"}
    :cities #{"Moscow" "Saint Petersburg" "Warsaw" "Stevastapol"} })

(def turkey
  { :armies #{"Constantinople" "Smyrna"}
    :fleets #{"Ankara"}
    :cities #{"Ankara" "Constantinople" "Smyrna"}})
