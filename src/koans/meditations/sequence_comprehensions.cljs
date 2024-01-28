(ns koans.meditations.sequence-comprehensions)

(def koans '(
  "Compreensão de sequências pode definir em um símbolo cada elemento na sua vez"
  (= :__
     (for [index (range 6)]
       index))

  "Elas podem facilmente replicar usar map"
  (= '(0 1 4 9 16 25)
     (map (fn [index] (* index index))
          (range 6))
     (for [index (range 6)]
       :__))

  "E também filtrar (filter)"
  (= '(1 3 5 7 9)
     (filter odd? (range 10))
     (for [index :__ :when (odd? index)]
       index))

  "Combinações dessas transformações é trivial"
  (= '(1 9 25 49 81)
     (map (fn [index] (* index index))
          (filter odd? (range 10)))
     (for [index (range 10) :when :__]
       :__))

  "Transformações mais complexas simplesmente recebem múltiplos símbolos."
  (= [[:topo :esquerda] [:topo :meio] [:topo :direita]
      [:meio :esquerda] [:meio :meio] [:meio :direita]
      [:baixo :esquerda] [:baixo :meio] [:baixo :direita]]
       (for [linha [:topo :meio :baixo]
             coluna [:esquerda :meio :direita]]
         :__))
))
