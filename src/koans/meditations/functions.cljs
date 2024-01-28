(ns koans.meditations.functions)

(def koans '(
 "Executar uma função é como dar um abraço nela com parênteses"
  (= :__ (eleva-ao-quadrado 9))

  "Funções são geralmente definidas antes de serem usadas"
  (= :__ (mutiplica-por-10 2))

  "Mas elas também podem ser definidas e usadas ao mesmo tempo"
  (= :__ ((fn [n] (* 5 n)) 2))

  "Ou até mesmo usando uma sintaxe mais curta"
  "(= :__ (#(* 15 %) 4))"

  "Mesmo funções anônimas podem receber múltiplos argumentos"
  "(= :__ (#(+ %1 %2 %3) 4 5 6))"

  "Argumentos também podem ser ignorados"
  "(= :__ (#(* 15 %2) 1 2))"

  "Uma função pode retornar outra"
  (= 9 (((fn [] :__)) 4 5))

  "Funções também podem receber funções como argumentos"
  (= 20 ((fn [f] (f 4 5))
           :__))

  "Funções de ordem superior recebem funções como argumentos"
  (= 25 (:__
          (fn [n] (* n n))))

  "Mas elas normalmente ficam melhores usando nomes de funções como argumento"
  (= 25 (:__ eleva-ao-quadrado))
))

(def fns [
  "(defn mutiplica-por-10 [n]
  (* 10 n))"
  '(defn eleva-ao-quadrado [n] (* n n))])
