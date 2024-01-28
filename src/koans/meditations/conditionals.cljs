(ns koans.meditations.conditionals)

(def koans '(
  "Você encontrará muitas decisões"
  (= :__ (if (false? (= 4 5))
          :a
          :b))

  "Algumas delas não te deixam alternativas"
  (= :__ (if (> 4 3)
          []))

  "E nessa situação você pode encontrar o nada"
  (= :__ (if (nil? 0)
          [:a :b :c]))

  "Em outras sua alternativa pode ser interessante"
  (= :gloria (if (not (empty? ()))
               :destruicao
               :__))

  "Você pode ter muitos caminhos possíveis"
  (let [x 5]
    (= :seu-caminho (cond (= x :__) :caminho-nao-seguido
                          (= x :__) :outro-caminho-nao-seguido
                          :else :__)))

  "Ou seu destino pode estar selado"
  (= :__ (if-not (zero? :__)
          'destruicao
          'mais-destruicao))

  "Em caso de emergência, vá muito rápido"
  (= :muito-rapido
     (explique-velocidade-exercicio :__))

  "Mas admita quando você não sabe o que fazer"
  (= :__
     (explique-velocidade-exercicio :assistir-youtube))
))

(def fns [
  "(defn explique-velocidade-exercicio [tipo-exercicio]
     (case tipo-exercicio
           :ciclismo  :muito-rapido
           :jog       :medio
           :caminhada :nada-rapido
           :que-exercicio?))"
  ])
