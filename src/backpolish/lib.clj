(ns backpolish.lib)

(use '[clojure.string :only (split)])

(defn get_tokens [str]
  (seq (split str #"\s+")))

(defn eval_tokens
  ([tokens] (eval_tokens tokens (vec [])))
  ([tokens stack]
    (let [ current (first tokens)
           other (rest tokens) ]
      (if current
        (cond
          (not= (.indexOf "+-*/" current) -1) (let [func (eval (symbol current))
                                                    newval (func (peek stack)
                                                           (peek (pop stack)))
                                                    newstack (pop (pop stack))]
                                                (recur other (conj newstack newval)))
          :else (recur other
                       (conj stack
                             (read-string current))))


      (peek stack))))
)

(defn calc [str]
  (eval_tokens (if (string? str)
                 (get_tokens str)
                 str)))
