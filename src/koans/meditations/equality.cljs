(ns koans.meditations.equality)

(def koans '(
  "Devemos contemplar a verdade testando a realidade, usando a igualdade"
  (= :__ true)

  "Para entender a realidade, precisamos comparar nossas expectativas com a realidade"
  (= :__ (+ 1 1))

  "Podemos testar a igualdadae de muitas coisas"
  (= (+ 3 4) 7 (+ 2 :__))

  "Algumas coisas parecem diferentes mas são a mesma"
  "(= :__ (= 2 2/1))"

  "Cuidado com strings, as aparências enganam"
  (= :__ (= 2 "2"))

  "Alguma coisa não é igual a nada"
  (= :__ (not (= 1 nil)))

  "Strings, e keywords, e símbolos minha nossa!"
  (= :__ (= "foo" :foo 'foo))

  "Crie uma keyword a partir de outro tipo"
  (= :foo (keyword :__))

  "Simbolismo está em todo lugar ao nosso redor"
  (= 'foo (symbol :__))

  "O que pode ser igual ao nada?"
  (= :__ nil)

  "Quando as coisas não podem ser iguais, elas tem que ser diferentes"
  (not= :igual :__)
))
