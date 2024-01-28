(ns koans.meditations.atoms)

(def koans '(
  "Átomos são referencias para valores"
  "(= :__ (deref relogio-atomico))"

  "Você pode obter o valor de forma mais sucinta"
  "(= :__ @relogio-atomico)"

  "Você pode até mesmo mudar o valor usando swap!"
  "(= :__ (do
          (swap! relogio-atomico inc)
          @relogio-atomico))"

  "Mantenha os impostos fora disso: usar swap! não precisa de transações"
  "(= 5 (do
         :__
         @relogio-atomico))"

  "Qualquer número de argumentos podem ser usados em um swap!"
  "(= :__ (do
          (swap! relogio-atomico + 1 2 3 4 5)
          @relogio-atomico))"

  "Átomos atômicos são atômicos"
  "(= :__ (do
          (compare-and-set! relogio-atomico 100 :fin)
          @relogio-atomico))"

  "Quando suas expectativas estão alinhadas com a realidade das coisas, prossiga em seu caminho"
  "(= :fin (do
            (compare-and-set! :__)
            @relogio-atomico))"
))

(def fns [
  '(def relogio-atomico (atom 0))
])
