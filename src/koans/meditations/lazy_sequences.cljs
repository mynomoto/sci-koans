(ns koans.meditations.lazy-sequences)

(def koans '(
  "Existem muitas maneiras de gerar uma sequência"
  (= :__ (range 1 5))

  "O padrão do intervalo (range) é começar pelo 0"
  (= :__ (range 5))

  "Pegue (take) apenas o que você precisa se a sequência é grande"
  (= [0 1 2 3 4 5 6 7 8 9]
     (take :__ (range 100)))

  "Ou limite os resultados ignorando (drop) o que você não precisa"
  (= [95 96 97 98 99]
     (drop :__ (range 100)))

  "Iteração (iterate) retorna uma sequência tardia infinita"
  (= :__ (take 20 (iterate inc 0)))

  "Repetição é chave"
  (= [:a :a :a :a :a :a :a :a :a :a ]
     (repeat 10 :__))

  "Iteração pode ser usada para repetição (repeat)"
  (= (repeat 100 :foo)
     (take 100 (iterate :__ :foo)))
))
