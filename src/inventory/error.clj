;; (defn error-message
;;   [severity]
;;   (str "Oh! An error has ocurred..."
;;        (if (= severity "MILD")
;;          " But do not worry. It was Mild"
;;          " Jeez! It was serious.")))

;; (defn error-message
;;   [severity]
;;   (let [message-prefix "Oh! An error has occurred... "]
;;     (case severity
;;       "MILD" (str message-prefix " But do not worry. It was mild.")
;;             (str message-prefix " Jeez! It was serious."))))

(defn error-message
  [severity]
  (let [message-prefix "Oh! An error has occurred... "]
    (cond (= severity "MILD") (str message-prefix " But do not worry. It was mild.")
          (= severity "SERIOUS") (str message-prefix " Jeez! It was serious.")
          :else "INVALID SEVERITY!!!")))


(error-message "MILD")
(error-message "SERIOUS")