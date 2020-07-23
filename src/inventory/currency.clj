(ns inventory.currency)

(defn usd-to-brl
  [usd]
  (let [conversion-rate 5.88]
    (* usd conversion-rate)))

(defn chf-to-usd
  [chf]
  (let [conversion-rate 1.03]
    (* chf conversion-rate)))

(def chf-to-brl (comp usd-to-brl chf-to-usd))

(chf-to-brl 100)

(assert (= 605.64 (chf-to-brl 100.0)))
