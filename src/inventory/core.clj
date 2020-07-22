(ns inventory.core)

(println "Welcome to the Inventory System")

; predicate
(defn more-expensive-than? [minimum price]
  (> price minimum))

(more-expensive-than? 100 100)
(more-expensive-than? 100 1000)

; predicate
(defn draw-lottery
  ([_] (draw-lottery))
  ([ ] (> (rand) 0.5)))

(draw-lottery)
(draw-lottery 100)

(defn discounted-price [apply-discount? original-price]
  (println "entered here!!!")
  (if (apply-discount? original-price)
    (let [discount-tax (/ 10 100)
          discount (- 1 discount-tax)
          discounted-price (* original-price discount)]
      discounted-price)
    original-price))

; higher order functions: you can pass functions as arguments, return functions as values, anonymous functions

(println (discounted-price (fn [price] (more-expensive-than? 100 price)) 100))
(println (discounted-price (partial more-expensive-than? 100) 1000))

(println (discounted-price draw-lottery 100))
(println (discounted-price draw-lottery 1000))

; anonymous function (lambda)
(println (discounted-price
           (fn [price] (and (>= price 50) (<= price 200)))
           100))

; another syntax for anonymous functions (lambda)
(println (discounted-price
           #(and (>= % 500) (<= % 2000))
           100))

((fn [price] (>= price 50)) 1000)




















