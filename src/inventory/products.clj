(ns inventory.products
  (:use clojure.pprint))

; associate key -> value
; product -> number in the inventory
; Backpack -> 10
; Shirt -> 5

; maps (associative data structures)

;(def inventory {"Backpack" 10, "Shirt" 5})
(def inventory {:backpack 10, :shirt 5})                    ; keywords as keys
(inventory :backpack)
(:backpack inventory)

(count inventory)

(keys inventory)
(vals inventory)

(assoc inventory :chair 3)
(dissoc inventory :shirt)

(inc 9)
(dec 9)

(update inventory :backpack dec)
(update inventory :backpack #(- % 3))

(def order {:product/backpack
                           {:quantity 2
                            :price 80}
            :product/shirt
                           {:quantity 3
                            :price 40}})

(println order)

(pprint order)

(keys order)
(vals order)

;(keys (vals order))

(assoc order :product/keyring {:quantity 10
                                :price 1})


(order :product/backpack)
(order :product/pen)                                        ; didn't get an error

(get order :product/backpack)
(get order :product/pen)                                    ; nil
(get order :product/pen {})

(:product/backpack order)

(:product/pen order)                                        ; nil

(:price (:product/backpack order))


;(update order :product/backpack inc)   ; error                      ; error
;(update order :quantity inc) ; error

(update-in order [:product/backpack :quantity] inc)
(get-in order [:product/backpack :quantity])
(assoc-in order [:product/backpack :quantity] 7)


(:quantity (:product/backpack order))

; threading macro (not concurrent programming, but sewing)
(-> order
    :product/backpack
    :quantity
    pprint)

; pipeline (similar to ps -ef | grep java | wc -l)

(pprint order)
(map pprint order)

;(defn my-print [element]
;  (pprint "my-print!!!")
;  (pprint element)
;  (pprint (element 0))
;  (pprint (element 1))
;  (class element))

; destructuring
(defn my-print [[key value]]
  (println key " => " value))

(map my-print order)

;(vals order)
;(keys (vals order))

; clojure.lang.MapEntry
; 0 is a key
; 1 is a value

; calculate the prices of the products is this order

(defn pricing
  [[_ details]]
  (* (:quantity details) (:price details)))

(map pricing order)

; total price $ of this order
(defn total-price [order]
  (reduce + (map pricing order)))

(total-price order)

; -> Threading first (output of the previous function as the FIRST argument)
;(defn total-price [order]
;  (-> order
;      (map ,,,,,,,, pricing)
;      (reduce ,,,,,,, +)))
;
;(reduce (map order pricing) +)

; ->> Threading last (output of the previous function as the LAST argument)
(defn total-price [order]
    (->> order
        (map pricing ,,,,,,,,)
        (reduce + ,,,,,,,)))

(total-price order)

; don't want to destructure
(defn pricing [product]
  (pprint product)
  (* (:quantity product) (:price product)))

; more common than destructuring
(defn total-price [order]
  (->> order
       vals
       (map pricing)
       (reduce +)))

(vals order)

(total-price order)


(def order {:product/backpack {:quantity 2
                               :price 80}
            :product/shirt {:quantity 3
                            :price 40}
            :product/keyring {:quantity 10
                              :price 0}
            :product/pen {:quantity 5}})

(defn free? [product-details]
  ;(println (class product-details))
  (<= (get product-details :price 0) 0))

(-> order :product/pen)
(-> order :product/pen :price)
(= nil 0)
(< nil 0)
(<= nil 0)

(get (-> order :product/pen) :price 0)

(free? (:product/backpack order))
(free? (:product/shirt order))
(free? (:product/keyring order))
(free? (:product/pen order))

(vals order)

(filter free? (vals order))

(filter free? order)

(filter (fn [[_ details]] (free? details)) order)

(filter #(free? %) order)

(first [:key "value"])
(second [:key "value"])

(filter #(free? (second %)) order)


(defn paid? [product-details]
  (not (free? product-details)))

(filter free? (vals order))
(filter paid? (vals order))

(free? {:price 80})
(not (free? {:price 80}))
((comp not free?) {:price 80})

; function composition
(def paid? (comp not free?))

(defn total-price [order]
  (->> order
       vals
       (filter paid?)
       (map pricing)
       (reduce +)))
(total-price order)

