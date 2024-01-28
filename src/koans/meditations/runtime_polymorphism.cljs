(ns koans.meditations.runtime-polymorphism)

(def koans '(
  "Algumas funções podem ser usadas de formas diferentes - sem argumentos"
  (= :__ (ola))

  "Com um argumento"
  (= :__ (ola "mundo grande"))

  "Ou com muitos argumentos"
  (= :__
     (ola "Pedro" "Paulo" "Maria"))

  "Multimétodos permitem usos mais complexos"
  (= "Bambi come vegetais."
     (dieta {:especie "cervo" :nome "Bambi" :idade 1 :tipo-dieta :herbivoro}))

  "Animals tem nomes diferentes"
  (= "Thumper come vegetais."
     (dieta {:especie "rabbit" :nome "Thumper" :idade 1 :tipo-dieta :herbivoro}))

  "Diferentes métodos são udasdos dependendo do resultado da função de escolha"
  (= "Simba come animais."
     (dieta {:especie "leão" :nome "Simba" :idade 1 :tipo-dieta :carnivoro}))

  "Você pode usar um método padrão (:default) quando não existe um específico"
  (= "Eu não sei o que Rich Hickey come."
     (dieta {:nome "Rich Hickey"}))
))

(def fns [
  "(defn ola
  ([] \"Olá mundo!\")
  ([a] (str \"Olá, \" a \".\"))
  ([a & more] (str \"Olá para este grupo: \"
                   (apply str
                          (interpose \", \" (concat (list a) more)))
                   \"!\")))"
  "(defmulti dieta (fn [x] (:tipo-dieta x)))"
  "(defmethod dieta :herbivoro [a] :__)"
  "(defmethod dieta :carnivoro [a] :__)"
  "(defmethod dieta :default [a] :__)"
])
