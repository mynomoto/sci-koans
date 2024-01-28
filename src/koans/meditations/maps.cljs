(ns koans.meditations.maps)

(def koans '(
  "Não fique perdido ao criar um mapa"
  (= {:a 1 :b 2} (hash-map :a 1 :__))

  "Um valor precisa existir para cada chave"
  (= {:a 1} (hash-map :a :__))

  "`count` conta as entradas do mapa (uma entrada é um par de chave/valor)"
  (= :__ (count {:a 1 :b 2}))

  "Você pode encontrar o valor para uma chave"
  (= :__ (get {:a 1 :b 2} :b))

  "Mapas podem ser usados como funções para encontrar valores de chaves"
  (= :__ ({:a 1 :b 2} :a))

  "keywords também"
  (= :__ (:a {:a 1 :b 2}))

  "Mas chaves de mapas não precisam ser keywords"
  (= :__ ({2006 "Itália" 2010 "Espanha" 2014 "Alemanha"} 2014))

  "Você pode não conseguir encontrar um valor para uma chave"
  (= :__ (get {:a 1 :b 2} :c))

  "Mas você pode definir seu próprio padrão"
  (= :__ (get {:a 1 :b 2} :c :chave-nao-encontrada))

  "Você pode descobrir se uma chave está presente no mapa"
  (= :__ (contains? {:a nil :b nil} :b))

  "Ou se não está presente"
  (= :__ (contains? {:a nil :b nil} :c))

  "Mapas são imutáveis, mas você pode criar uma versão nova e melhor"
  (= {1 "Janeiro" 2 :__} (assoc {1 "Janeiro" } 2 "Fevereiro"))

  "Você também pode criar uma versão nova com uma entrada removida"
  (= :__ (dissoc {1 "Janeiro" 2 "Fevereiro"} 2))

  "Crie um novo mapa combinando outros mapas"
  (= {:a 1 :b 2 :__ :__} (merge {:a 1 :b 2} {:c 3}))

  "Defina como fazer quando as entradas tiverem as mesmas chaves ao combinar mapas"
  (= {:a 1 :b :__ :c 3} (merge-with + {:a 1 :b 1} {:b 1 :c 3}))

  "Frequentemente você vai precisar das chaves, mas a ordem não é confiável"
  (= (list :__ )
     (sort (keys { 2006 "Itália" 2010 "Espanha" 2014 "Alemanha"})))

  "Você pode obter os valores de forma parecida"
  (= (list :__)
     (sort (vals { 2006 "Itália" 2010 "Espanha" 2014 "Alemanha"})))

  "Você pode até iterar sobre as entradas do mapa como uma seq"
  (= {:a :__ :b :__}
     (into {}
           (map
            (fn [[k v]] [k (inc v)])
            {:a 1 :b 2})))
))
