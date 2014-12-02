(ns diplomacy.board)

; a game consists of powers with armies, fleets, and cities.
(def game {
            :russia  { :armies #{ 'Moscow 'Warsaw }
                       :fleets #{ 'Sevastapol (with-meta 'Saint-Petersburg { :coast :south })}
                       :cities #{ 'Moscow 'Warsaw' 'Sevastapol 'Saint-Petersburg }}

            :austria { :armies #{ 'Vienna 'Budapest }
                       :fleets #{ 'Trieste }
                       :cities #{ 'Vienna 'Budapest 'Trieste }}})

(def city-set #{'Saint-Petersburg
                'Moscow
                'Warsaw
                'Sweden
                'Finland
                'Vienna
                'Budapest
                'Trieste })


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
                'Trieste })

(def water-set #{'Black-Sea
                 'Gulf-of-Bothnia
                 'Barents-Sea
                 'Baltic-Sea
                 'Norwegian-Sea })


(def boundaries #{ #{'Moscow 'Saint-Petersburg}
                   #{'Moscow 'Livonia}
                   #{'Moscow 'Warsaw}
                   #{'Moscow 'Ukraine}
                   #{'Moscow 'Stevastapol}
                   ; StP. is coastally adjacent along the north...
                   #{ (with-meta 'Saint-Petersburg {:coast :north}) 'Barents-Sea }
                   #{ (with-meta 'Saint-Petersburg {:coast :north}) 'Norway }
                   ; StP. is coastally adjacent along the south...
                   #{ (with-meta 'Saint-Petersburg {:coast :south}) 'Gulf-of-Bothnia}
                   #{ (with-meta 'Saint-Petersburg {:coast :south}) 'Finland}
                   #{ (with-meta 'Saint-Petersburg {:coast :south}) 'Livonia}
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
                   #{'Norway 'Barents-Sea}})


(def russia
  { :armies #{'Moscow 'Warsaw}
    :fleets #{^:south 'Saint-Petersburg 'Stevastapol}
    :cities #{'Moscow 'Saint-Petersburg 'Warsaw 'Stevastapol} })

(def turkey   #{'Saint-Petersburg
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
                'Trieste })

