(ns koans.meditations.creating-functions)

(def koans '(
  "Alguém pode saber o que procura sabendo o que não procura"
  (= [:__] (let [not-a-symbol? (complement symbol?)]
                  (map not-a-symbol? [:a 'b "c"])))

  "Elogios e complemento (complement) pode te ajudar a separar o joio do trigo"
  (= [:trigo "trigo" 'trigo]
       (let [not-nil? :__]
         (filter not-nil? [nil :trigo nil "trigo" nil 'trigo nil])))

  "Funções parciais (partial) permitem a procrastinação"
  (= 20 (let [multiplica-por-5 (partial * 5)]
          (:__)))

  "Não se esqueça, as primeiras coisas vem primeiro"
  (= [:__]
       (let [ab-adder (partial concat [:a :b])]
         (ab-adder [:__])))

  "Funções podem se unir em uma função composta (comp)"
  (= 25 (let [incrementa-e-eleva-ao-quadrado (comp eleva-ao-quadrado inc)]
          (incrementa-e-eleva-ao-quadrado :__)))

  "Experimente um decremento (dec) duplo"
  (= :__ (let [duplo-dec (comp dec dec)]
          (duplo-dec 10)))

  "Tenha cuidado com a ordem que você combina suas funções"
  (= 99 (let [eleva-ao-quadrado-e-dec :__]
          (eleva-ao-quadrado-e-dec 10)))
))

(def fns [
  '(defn eleva-ao-quadrado [x] (* x x))
])
