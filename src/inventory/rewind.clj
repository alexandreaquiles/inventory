(ns inventory.rewind)

(def minimum-days 2)
(def base-price 2)
(def default-day-price 1.5)
(def release-day-price 3)

(defn regular-rental-amount [days]
  (+ base-price (* (- days minimum-days) default-day-price)))

(defn release-rental-amount [days]
  (* days release-day-price))

;(defn rental-amount
;  [type days]
;  (cond
;    (= type "REGULAR") (regular-rental-amount days)
;    (= type "RELEASE") (release-rental-amount days)
;    :else (str "Type should be REGULAR or RELEASE")))

(defn receipt [name movie rental-calculation-function days]
  (str "Receipt for" name "Rental" movie "Total amount" (rental-calculation-function days)))

(defn print-receipt [name movie rental-calculation-function days]
  (println (receipt name movie rental-calculation-function days)))

; a string that represents the type of the movie
;(print-receipt "Alexandre" "Back to the Future" "REGULAR" 5)
;(print-receipt "Andreia" "The NeverEnding Story" "REGULAR" 2)
;(print-receipt "Carlos" "Rocky VII" "RELEASE" 3)

; a FUNCTION that CALCULATES the PRICE of the movie
(print-receipt "Alexandre" "Back to the Future" regular-rental-amount 5)
(print-receipt "Andreia" "The NeverEnding Story" regular-rental-amount 2)
(print-receipt "Carlos" "Rocky VII" release-rental-amount 3)

