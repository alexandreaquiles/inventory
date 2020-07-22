(ns inventory.products
  (:use clojure.pprint))

; associate key -> value
; product -> number in the inventory
; Backpack -> 10
; Shirt -> 5

; maps (associative data structures)

;(def inventory {"Backpack" 10, "Shirt" 5})
(def inventory {:backpack 10, :shirt 5})                    ; keywords as keys
(count inventory)

(keys inventory)
(vals inventory)

(assoc inventory :chair 3)
(dissoc inventory :shirt)

(inc 9)
(dec 9)

(update inventory :backpack dec)
(update inventory :backpack #(- % 3))

(def order {:product/backpack {:quantity 2
                       :price 80}
            :product/shirt {:quantity 3
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


;(update order :product/backpack inc)                        ; error
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