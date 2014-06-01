(ns diplomacy.core
  [:require clojure.set]
;  [:require clojure.core.logic :as logic]
  [:use diplomacy.board])


(defn adjacent [prov]
  (apply clojure.set/union
         (filter (fn [b] (contains? b prov)) boundaries)))


(defn coastal? [prov]
  (some waters (adjacent prov)))


(defn adjacent? [p1 p2]
  (contains? boundaries #{p1 p2}))


(defn adjacent-land? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} lands)))


(defn adjacent-water? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} waters)))


(defn adjacent-coast? [p1 p2 & coast]
  (let [boundary (get boundaries #{p1 p2})]
    ; are these adjacent?
    ; do they share a water boundary?
    (and
     (adjacent? p1 p2)
     (coastal? p1)
     (coastal? p2)
     (= (first coast) (meta boundary)))))


; A power may not have more units than cities.
(defn balance [power]
  (- (count (:cities power))
     (+ (count (:armies power)) (count (:fleets power)))))
