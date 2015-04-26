(ns diplomacy.rules
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.pldb :as d]
            [clojure.core.logic.unifier :as u]))

(d/db-rel adjacent province & neighbors)
(d/db-rel water province)
(d/db-rel land province)
(d/db-rel city province)
(d/db-rel neighbor p1 p2)

(def europe
  (d/db
   [neighbor :Bulgaria :Rumania]
   [neighbor :Bulgaria :Serbia] 
   [neighbor :Bulgaria :Constantinople]
   [neighbor :Bulgaria :Constantinople :along :east-coast] ;; of what, bulgaria or const?
   [neighbor :Bulgaria :Constantinople :along :south-coast]
   [neighbor :Bulgaria :Black-Sea      :along :east-coast]
   [neighbor :Bulgaria :Aegean-Sea     :along :south-coast]
   [neighbor :Bulgaria :Greece]
   [coast    :Bulgaria :east]
   [coast    :Bulgaria :south]
   [neighbor :Constantinople :Black-Sea]
   [neighbor :Constantinople :Aegean-Sea]
   [neighbor :Constantinople :Ankara]
   [neighbor :Constantinople :Smyrna]
   [water    'Black-Sea]
   [water    'Aegean-Sea]
   [city     'Bulgaria]
   [city     'Ankara]
   [city     'Smyrna]
   [city     'Constaninople]))

(defn neighbors [p db]
  (logic/run-db* db [q]
    (logic/fresh [x]
      (logic/conde
        [(neighbor p x)]
        [(neighbor x p)])
      (logic/== q x))))

(defn coastal? [p db & args]
  (logic/run-db* db [q]
    (logic/fresh [x]
      (neighbor p x)
        (water x)
        (logic/== q x))))

(defn inland? [province db & args]
  (logic/run-db* db [query]
    (logic/fresh [other]
      (neighbor province other)
      (water other)
      (logic/!= query other))))

(defn adjacent? [p1 p2 db]
  (logic/run-db* db [q]
    (logic/fresh [x]
      (neighbors p1 db))))
