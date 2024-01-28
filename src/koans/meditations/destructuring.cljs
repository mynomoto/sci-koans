(ns koans.meditations.destructuring)

(def koans '(
  "Desestruturação é um juiz: ela quebra argumentos"
  (= :__ ((fn [[a b]] (str b a))
         [:foo :bar]))

  "Seja em definições de funções"
  (= (str "Primeiro vem o amor, "
          "depois o casamento, "
          "depois vem Clojure com o carrinho de bebê")
     ((fn [[a b c]] :__)
      ["amor" "casamento" "Clojure"]))

  "Ou em expressões let"
  (= "Rich Hickey aka The Clojurer aka Go Time aka Macro Killah"
     (let [[first-name last-name & aliases]
           (list "Rich" "Hickey" "The Clojurer" "Go Time" "Macro Killah")]
       :__))

  "Você pode conseguir de volta o argumento completo, se você gosta de argumentar"
  (= {:partes-originais ["Stephen" "Hawking"] :partes-com-nomes {:primeiro "Stephen" :ultimo "Hawking"}}
     (let [[primeiro-nome sobrenome :as nome-inteiro] ["Stephen" "Hawking"]]
       :__))

  "Quebrar mapas por chaves"
  (= "Rua dos Testes 123, Cidade dos Testes, XX"
     (let [{endereco :endereco, cidade :cidade, estado :estado} endereco-teste]
       :__))

  "Ou de forma mais sucinta"
  (= "Rua dos Testes 123, Cidade dos Testes, XX"
   (let [{:keys [endereco :__]} endereco-teste]
     :__))

  "Tudo junto agora"
  (= "Teste Testado, Rua dos Testes 123, Cidade dos Testes, XX"
     (:__ ["Teste" "Testado"] endereco-teste))
))

(def fns [
  "(def endereco-teste
  {:endereco \"Rua dos Testes 123\"
   :cidade \"Cidade dos Testes\"
   :estado \"XX\"})"
])
