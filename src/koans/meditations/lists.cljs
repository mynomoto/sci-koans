(ns koans.meditations.lists)

(def koans '(
  "Listas pode ser criadas com uma função ou uma sexp quotada"
  (= '(:__) (list 1 2 3 4 5))

  "Elas são seqs (sequências), então elas permitem acessar o primeiro elemento"
  (= :__ (first '(1 2 3 4 5)))

  "E também ao resto dos elementos"
  (= :__ (rest '(1 2 3 4 5)))

  "Conte suas bençãos"
  (= :__ (count '(dracula dooku chocula)))

  "Antes que elas se vão"
  (= :__ (count '()))

  "O resto, quando nada sobra é vazio"
  (= :__ (rest '(100)))

  "Construção adicionando um elemento no início é fácil"
  (= :__ (cons :a '(:b :c :d :e)))

  "Juntar um elemento a uma lista é muito parecido"
  (= :__ (conj '(:b :c :d :e) :a))

  "Você pode usar a lista como uma pilha para obter o primeiro elemento"
  (= :__ (peek '(:a :b :c :d :e)))

  "Ou os outros"
  (= :__ (pop '(:a :b :c :d :e)))

  ;; ---
  ;"But watch out if you try to pop nothing"
  ;(= :__ (try
  ;        (pop '())
  ;        (catch IllegalStateException e
  ;          "No dice!")))

  ;"The rest of nothing isn't so strict"
  ;(= :__ (try
  ;        (rest '())
  ;        (catch IllegalStateException e
  ;          "No dice!")))
  ;))
))
