(ns diplomacy.province
  (:require [clojure.core.logic :as logic]
            [clojure.core.logic.pldb :as d]
            [clojure.core.logic.unifier :as u]))


;; Some examples...
;;
;; An army does not need to occupy a coast
;; If province has coasts, a fleet must occupy a specific coast.
;; If a fleet moves to land and the coast is ambiguous, the order is not valid.
;; A fleet cannot move from one coast to another.
;;
;; Spain:
;; land
;; city
;; has a north coast
;; has a south coast
;; adjacent to portugal, gascony, marseilles
;; north-coast adjacent to portugal, gascony, and the mid-atlantic
;; south-coast is adjacent to portugal, mid-atlantic, west-meditteranean, gulf-of-lyon, marseilles
;;
;; Portugal
;; is land
;; is a city
;; is adjacent to mid-atlantic
;; borders spain by land, north coast of spain, south coast of spain

;; Gascony
;; gascony is land
;; gascony borders spain
;; gascony connects to north coast of spain
;; gascony does not connect to the south coast of spain
;;
;;
;; 
;; 
;; Mid Atlantic
;; mid-atlantic is water
;; mid-atlantic is adjacent to
