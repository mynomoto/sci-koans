(ns koans.meditations.higher-order-functions)

(def koans '(
  "A função map cria uma sequência a partir de outra outra"
  (= '(:__) (map (fn [x] (* 4 x)) [1 2 3]))

  "Você pode definir a função para criar a nova sequência"
  (= '(1 4 9 16 25) (map (fn [x] :__) [1 2 3 4 5]))

  "Ou usar um nome de função que já existe"
  (= :__ (map nil? [:a :b nil :c :d]))

  "Um filtro (filter) pode ser forte"
  (= :__ (filter (fn [x] false) '(:qualquer :coisa :aqui)))

  "Ou muito fraco"
  (= :__ (filter (fn [x] true) '(:qualquer :coisa :aqui)))

  "Ou ficar no meio do caminho"
  (= [10 20 30] (filter (fn [x] :__) [10 20 30 40 50 60 70 80]))

  "map e filter podem ser combinados"
  (= [10 20 30] (map (fn [x] :__) (filter (fn [x] :__) [1 2 3 4 5 6 7 8])))

  "reduce pode aumentar o resultado"
  (= :__ (reduce (fn [a b] (* a b)) [1 2 3 4]))

  "Você pode começar de outro lugar"
  (= 2400 (reduce (fn [a b] (* a b)) :__ [1 2 3 4]))

  "Números não são a única coisa que você pode usar reduce"
  (= "palavra" (reduce (fn [a b]
                         (if (< :__) b a))
                       ["qual" "é" "a" "palavra" "mais" "longa"]))
))
