SCI Koans
==========================
https://mynomoto.github.io/sci-koans

Os SCI Koans são uma forma divertida e fácil de começar a programar em
Clojure(Script). Nenhuma experiência com Clojure ou ClojureScript é assumida ou
necessária, e como os Koans são executados no navegador, eles não precisam de
um ambiente de desenvolvimento para Clojure ou ClojureScript.

Eles foram adaptados dos [Koans de
ClojureScript](http://clojurescriptkoans.com), e traduzidos para português.

### Editando os Koans
Os Koans ficam na pasta `src/koans/meditations`. Para um dado conjunto, a
variável `koans` deve conter uma sequência com uma string de descrição e a
expressão de teste.
Dentro da expressão de teste, quaisquer ocorrências de `:__` serão substituídas
por um campo texto. Existem situações onde a função `pr-str` de ClojureScript
altera o que é mostrado pela expressão (por exemplo substituindo caracteres de
quote com uma expressão `(quote)`); se isso acontecer, você pode transformar a
expressão em uma string para ela ser mostrada exatamente como escrita.

Se você precisar definir novas funções para uma seção, as adicione no vetor
`fns`. Como os koans, uma função pode ser uma expressão quoted ou uma string, e
quaisquer ocorrências de `:__` serão substituídas com um campo texto. Se você
desejar especificar a indentação da função, os espaços são mantidos na forma de
string. De qualquer maneira o código vai aparecer com syntax highlighting
automaticamente.

Se você quiser criar uma nova categoria de koans, você vai também precisar
adicionar a nova categoria na estrutura em `meditations.cljs`.

Contribuindo
------------
Pull requests são bem vindos!


License
-------
The use and distribution terms for this software are covered by the Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can be found in the file epl-v10.html at the root of this distribution. By using this software in any fashion, you are agreeing to be bound by the terms of this license.
