(ns inventory.rewind-alternative)
; a solution by Shwetha Thammaiah

(def cost-first-two-days 2)
(def cost-post-first-two-days 1.50)
(def release-movie-cost 3)
(def REGULAR "REGULAR")
(def RELEASE "RELEASE")


(defn regular-rental-amount
  [rental-days]
  (if (<= rental-days 2)
    cost-first-two-days
    (+ cost-first-two-days (* cost-post-first-two-days (- rental-days 2)))))

(defn release-rental-amount
  [rental-days]
  (* rental-days release-movie-cost))

; closure (lexical scoping)
(defn receipt
  [client-name movie-name movie-type rental-days]

  (defn calculate-rental-amount []
    (if (= movie-type REGULAR)
      regular-rental-amount
      release-rental-amount))

  (str "receipt for " client-name
       " rental " movie-name
       " movie type " movie-type
       " total amount " ((calculate-rental-amount) rental-days)))

(defn print-receipt []
  (println (receipt "Alexandre" "Back to the Future" REGULAR 5))
  (println (receipt "Andreia" "The NeverEnding Story" REGULAR 2))
  (println (receipt "Carlos" "Rocky VII" RELEASE 3)))

(print-receipt)
