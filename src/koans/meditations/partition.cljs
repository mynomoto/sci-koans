(ns koans.meditations.partition)

(def koans '(
  "Para dividir uma coleção você pode usar a função partition (particionar)"
  "(= '((0 1) (2 3)) (:__ 2 (range 4)))"

  "Mas cuidado caso não hajam elementos suficientes para formar n sequências"
  (= '(:__) (partition 3 [:a :b :c :d :e]))

  "Você pode usar partition-all (particionar tudo) para também obter divisões com menos de n elementos"
  (= :__ (partition-all 3 (range 5)))

  "Se você precisar, você pode começar cada divisão pulando alguns elementos"
  "(= '((0 1 2) (5 6 7) (10 11 12)) (partition 3 :__ (range 13)))"

  "Considere completar a última divisão com alguns valores padrões..."
  "(= '((0 1 2) (3 4 5) (6 :ola)) (partition 3 3 [:__] (range 7)))"

  "... mas perceba que eles só vão completar até o tamanho da divisão"
  "(= '( (0 1 2) (3 4 5) :__) (partition 3 3 [:estas :sao \"minhas\" \"palavras\"] (range 7)))"
))
