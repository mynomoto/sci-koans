(ns koans.meditations.sets)

(def koans '(
  "Você pode criar um conjunto convertendo outra coleção"
  (= #{3} (set :__))

  "Contar um conjunto é como contar outras coleções"
  (= :__ (count #{1 2 3}))

  "Lembre que um conjunto é um conjunto *matemático*"
  (= :__ (set '(1 1 2 2 3 3 4 4 5 5)))

  "Você pode pedir a união de dois conjuntos"
  (= :__ (set/union #{1 2 3 4} #{2 3 5}))

  "E também a intersecção (elementos que estão nos dois conjutos)"
  (= :__ (set/intersection #{1 2 3 4} #{2 3 5}))

  "E não esqueça da diferença"
  (= :__ (set/difference #{1 2 3 4 5} #{2 3 5}))
))
