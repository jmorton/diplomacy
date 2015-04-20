(ns diplomacy.core
  [:require [clojure.set]]
  [:require [diplomacy.board :refer :all]])


(defn adjacent [prov]
  (apply clojure.set/union
         (filter (fn [b] (contains? b prov)) boundaries)))

(defn coastal? [prov]
  (some water-set (adjacent prov)))

(defn adjacent? [p1 p2]
  (contains? boundaries #{p1 p2}))

(defn adjacent-land? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} land-set)))

(defn adjacent-water? [p1 p2]
  (and
   (adjacent? p1 p2)
   (clojure.set/subset? #{p1 p2} water-set)))

(defn adjacent-coast? [p1 p2]
  (let [boundary (get boundaries #{p1 p2})
        b1 (get boundary p1)
        b2 (get boundary p2)]
    (and
     (not (contains? (meta boundary) :land-only))
     (adjacent? p1 p2)
     (coastal? p1)
     (coastal? p2)
     ; does the meta of the boundaries match the meta of the params
     (= (meta p1) (meta b1))
     (= (meta p2) (meta b2)))))

; A power may not have more units than cities.
(defn balance [power]
  (- (count (:cities power))
     (+ (count (:armies power)) (count (:fleets power)))))


(defn valid-move? [power [type src dest] game]
  (adjacent-land? src dest)
  (adjacent-coast? src dest)
  (adjacent-water? src dest))
