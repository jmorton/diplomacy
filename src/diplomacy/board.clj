(ns diplomacy.board)

; a game consists of powers with armies, fleets, and cities.
(def game {:russia  {:armies #{ 'Moscow 'Warsaw }
                     :fleets #{ 'Sevastapol (with-meta 'Saint-Petersburg { :coast :south })}
                     :cities #{ 'Moscow 'Warsaw' 'Sevastapol 'Saint-Petersburg }}

           :austria {:armies #{ 'Vienna 'Budapest }
                     :fleets #{ 'Trieste }
                     :cities #{ 'Vienna 'Budapest 'Trieste }}
           })

(def city-set #{'Saint-Petersburg
                'Moscow
                'Warsaw
                'Sweden
                'Finland
                'Vienna
                'Budapest
                'Trieste
                'London
                'Liverpool
                'Edinburgh})


(def land-set #{'Finland
                'Saint-Petersburg
                'Livonia
                'Moscow
                'Warsaw
                'Ukraine
                'Stevastapol
                'Sweden
                'Norway
                'Galacia
                'Bohemia
                'Tyrolia
                'Vienna
                'Budapest
                'Trieste
                'London
                'Wales
                'Liverpool
                'York
                'Edinburgh
                'Clyde})

(def water-set #{'Black-Sea
                 'Gulf-of-Bothnia
                 'Barents-Sea
                 'Baltic-Sea
                 'Norwegian-Sea
                 'North-Sea
                 'English-Channel
                 'Irish-Sea
                 'North-Atlantic
                 })


(def boundaries #{ #{'Moscow 'Saint-Petersburg}
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
                   #{'Liverpool 'North-Atlantic}})
