(ns inventory.bmi)

(defn bmi
  [weight height]
  (/ weight (* height height)))

(defn bmi-classification
  [weight height]
  (let [bmi (bmi weight height)]
    (cond
      (> bmi 25.0) "Overweight"
      (>= bmi 18.5) "Normal"
      :else "Underweight")))

(println "Alexandre" (bmi-classification 85.0 1.83))
(println "Andreia" (bmi-classification 55.0 1.66))
