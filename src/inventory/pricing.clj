(ns inventory.pricing)


(def quantities '(30 700 1000))                             ; list
(count quantities)
(conj quantities 120 )                                      ; first element
; (quantities 0) ; error

(class quantities)                                          ; clojure.lang.PersistentList

(def prices [30 700 1000])                                  ; vector

(class prices)                                              ; clojure.lang.PersistentVector

(prices 0)

;([30 700 1000] 0)

(prices 2)

(prices 999)

(get prices 0)
(get prices 2)
(get prices 999)

(get prices 2 0)
(get prices 999 0)

(conj prices 120)                                           ; last element

(count prices)

(update prices 0 brl-to-usd)

(get prices 0)

(defn brl-to-usd [amount]
  (* amount 5))

(map brl-to-usd prices)



;(defn discounted-price [original-price]
;  (if (> original-price 100)
;    (let [discount-tax (/ 10 100)
;          discount (- 1 discount-tax)
;          discounted-price (* original-price discount)]
;      discounted-price)
;    original-price))

;(map discounted-price prices)
;(filter more-expensive-than? prices)

(defn discounted-price [original-price]
  (let [discount-tax (/ 10 100)
        discount (- 1 discount-tax)
        discounted-price (* original-price discount)]
    discounted-price))

(defn more-expensive-than? [price]
  (> price 100))


(map discounted-price (filter more-expensive-than? prices))


(range 10)
(range 2 10)

(even? 10)
(even? 11)

(filter even? (range 10))

(map #(* % %) (filter even? (range 10)))

(reduce + prices)

(defn my-sum [a b]
  (println "adding" "a=" a ", b=" b)
  (+ a b))

(range 5)
(reduce my-sum (range 5))
(reduce my-sum [10])
;(reduce my-sum []) ; error

(reduce my-sum prices)
(reduce my-sum 0 prices)                                    ; initial value
(reduce my-sum 0 [])
