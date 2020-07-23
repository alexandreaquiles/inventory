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
  (str "Receipt for: " name " Rental: " movie " Total amount: " (rental-calculation-function days)))

(defn print-receipt [name movie rental-calculation-function days]
  (println (receipt name movie rental-calculation-function days)))

; a string that represents the type of the movie
;(print-receipt "Alexandre" "Back to the Future" "REGULAR" 5)
;(print-receipt "Andreia" "The NeverEnding Story" "REGULAR" 2)
;(print-receipt "Carlos" "Rocky VII" "RELEASE" 3)

; a FUNCTION that CALCULATES the PRICE of the movie
;(print-receipt "Alexandre" "Back to the Future" regular-rental-amount 5)
;(print-receipt "Andreia" "The NeverEnding Story" regular-rental-amount 2)
;(print-receipt "Carlos" "Rocky VII" release-rental-amount 3)

(def period [2 3 2 5 6 2 3 4 2])

(reduce + (map regular-rental-amount period))

(reduce +
        (map regular-rental-amount
             (filter #(> % 2) period)))


(def rental-values {:regular regular-rental-amount
                    :release release-rental-amount})

((:regular rental-values) 2)
((rental-values :regular) 2)


((:release rental-values) 2)


;(defn print-receipt
;  [{:keys [name movie days]}]
;  (let [{movie-name :title movie-type :type} movie
;        rental-calculation (movie-type rental-values)]
;    (println (receipt name movie-name rental-calculation days))))

(defn print-receipt [rental]
  (let [name (:name rental)
        movie (:movie rental)
        movie-name (:title movie)
        calculation-function (-> movie :type rental-values)
        days (:days rental)]
        (println (receipt name movie-name calculation-function days))))

(print-receipt {:name  "Alexandre"
                :movie {:title "Back to the Future"
                        :type   :regular}
                :days  5})

(print-receipt {:name  "Andreia"
                :movie {:title "The NeverEnding Story"
                        :type   :regular}
                :days  2})

(print-receipt {:name  "Carlos"
                :movie {:title "Rocky VII"
                        :type   :release}
                :days  3})

(def history [{:name  "Alexandre"
               :movie {:title "Back to the Future"
                       :type   :regular}
               :days  5},
              {:name  "Andreia"
               :movie {:title "The NeverEnding Story"
                       :type   :regular}
               :days  2},
              {:name  "Carlos"
               :movie {:title "Rocky VII"
                       :type   :release}
               :days  3}])

(defn bill
  [rental-info]
  (let [days (:days rental-info)
        movie-type (get-in rental-info [:movie :type])
        rental-calculation (movie-type rental-values)]
    (rental-calculation days)))

(defn history-amount
  []
  (->> history (map bill) (reduce +)))

(println (history-amount))

