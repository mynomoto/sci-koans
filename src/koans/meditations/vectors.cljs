(ns koans.meditations.vectors)

(def koans '(
  "Você pode usar vetores como um tipo de estrutura de matris"
  (= :__ (count [42]))

  "Você pode criar um vetor a partir de uma lista"
  (= :__ (vec '(1)))

  "Ou a partir de alguns elementos"
  (= :__ (vector nil nil))

  "Mas você pode criar um vetor com quaisquer número de elementos"
  (= [1 :__] (vec '(1 2)))

  "conj em um vetor adiciona o novo elemento no final do vetor"
  (= :__ (conj [111 222] 333))

  "Você pode obter o primeiro elemento de um vetor assim"
  (= :__ (first [:manteiga :amendoim :e :geleia]))

  "E o último de forma similar"
  (= :__ (last [:manteiga :amendoim :e :geleia]))

  "Ou qualquer índice se você quiser"
  (= :__ (nth [:manteiga :amendoim :e :geleia] 3))

  "Você também pode obter uma parte do vetor"
  (= :__ (subvec [:manteiga :amendoim :e :geleia] 1 3))

  "Igualdade com outras coleções depende apenas dos valores"
  (= (list 1 2 3) (vector 1 2 :__))
))
