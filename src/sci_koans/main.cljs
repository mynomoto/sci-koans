(ns sci-koans.main
  (:require
    [clojure.string :as str]
    ;; All the configs
    sci.configs.applied-science.js-interop
    sci.configs.cljs.pprint
    sci.configs.cljs.test
    sci.configs.funcool.promesa
    sci.configs.hoplon.javelin
    sci.configs.hoplon.hoplon
    hoplon.dom
    [sci.core :as sci]
    [sci.ctx-store :as store]

    ; Legacy
    [cljs.tools.reader :refer [read-string]]
    [clojure.set :as set]
    [koans.meditations :as meditations]
    [jayq.util :refer [log wait]]
    [jayq.core :as $ :refer [$]]
    [dommy.core :as dommy])
  (:require-macros
    [dommy.macros :refer [deftemplate]]))

;; Necessary to avoid the error 'Attempting to call unbound fn: #'clojure.core/*print-fn*'
;; when calling `println` inside the evaluated code
(enable-console-print!)
(sci/alter-var-root sci/print-fn (constantly *print-fn*))
(sci/alter-var-root sci/print-err-fn (constantly *print-err-fn*))
(sci/enable-unrestricted-access!)
;; ------------------------------------------------------------ SCI eval

(def all-configs ; vars so that we can extract ns info
  [#'sci.configs.applied-science.js-interop/config
   #'sci.configs.cljs.pprint/config
   #'sci.configs.cljs.test/config
   #'sci.configs.funcool.promesa/config
   #'sci.configs.hoplon.javelin/config
   #'sci.configs.hoplon.hoplon/config])

(def sci-ctx
  (->> all-configs
    (map deref)
    (reduce
      sci/merge-opts
      (sci/init {:classes {'js js/globalThis :allow :all}}))
    ;; in .cljc, take the :cljs branch; here b/c of the bug babashka/sci#906
    (#(assoc % :features #{:cljs}))))

(store/reset-ctx! sci-ctx)

(defn eval-code
  ([code]
   (try {:value (sci/eval-string* sci-ctx code)}
     (catch :default e
       (try (js/console.log "Evaluation failed:" (ex-message e)
              (some-> e ex-data clj->js))
         (catch :default _))
       {:error (str (.-message e)) :data (ex-data e)}))))

(defonce transitioning? (atom false))

(defn hash-objects [] (str/split (.-hash js/location) "/" ))

(defn current-koan-index [] (meditations/KoanIndex.
  (subs (first (hash-objects)) 1)
  (dec (last (hash-objects)))))

(defn update-location-hash []
  (let [koan (meditations/next-koan-index (current-koan-index))]
    (set! (.-hash js/location) (str (:category koan) "/" (inc (:index koan))))))

(def fadeout-time 600)
(def char-width 14)
(def enter-key 13)
(def parentheses-classes-count 7)

(defn parentheses-class-name [index]
  (str "parentheses-" (mod index parentheses-classes-count)))

(defn input-with-code-block [parts]
  (for [part parts]
    (cond
      (= part :input)
        [:span {:class "code"}
          [:span {:class "shadow"}]
          [:input {:name "code" :autocorrect "off" :autocapitalize "off"}]]
      (vector? part)
        [:span {:class (str "text " (parentheses-class-name (second part)))}
          (first part)]
      :else
        [:span {:class "text"}
          part])))

(deftemplate input-with-code
  [koan]
  [:div {:class (str "koan koan-" (:index (current-koan-index)))}
    [:div {:class "description"} (:description koan)]
    [:div {:class "code-box"}
      (input-with-code-block (:code-parts koan))]
      (when-not (nil? (:fn-strings koan))
        [:div {:class "functions"}
          (for [function (:fn-strings koan)]
            [:div {:class "function"}
              [:pre
                (input-with-code-block function)]])])])

(deftemplate error-message
  []
  [:div {:class "error"} "You have not yet attained enlightenment."])

(defn input-with-element-content [el]
  (->> ($/children ($ el))
       (map #(let [$el ($ %)]
              (cond ($/has-class $el "text") ($/text $el)
                    ($/has-class $el "code") ($/val ($ "input" $el)))))
       (str/join "")))

(defn valid-input? [el]
  ;; ensure input string contains at least one valid Clojure form
  (let [input ($/val ($ el))]
    (or (some? (try (read-string input) (catch :default _ nil)))
        (= (subs input 0 3) "nil"))))

(defn input-string []
  (if (not-every? valid-input? ($ ".code-box input"))
    ""
    (->> (concat ($ ".function pre")
                 ($ ".code-box"))
         (map input-with-element-content)
         (str/join " "))))

(defn load-next-koan []
  (update-location-hash))

(defn remove-active-koan []
  (let [$el ($ :.koan)]
    ($/fade-out $el
      #($/remove $el))))

(defn remove-static-pages []
  ($/fade-out ($ :.static)))

(defn category-name [koan-index]
  (let [category (:category koan-index)]
    (str/replace category "-" " ")))

(defn render-koan [koan]
  (remove-active-koan)
  (remove-static-pages)
  (let [$elem ($ (input-with-code koan))
        $category ($ :.category)
        current-category (category-name (current-koan-index))]
    (when-not (empty? (:fn-strings koan))
      ($/add-class $elem "has-functions"))
    (when (not (= ($/text $category) current-category))
      ($/fade-out $category))
    (wait fadeout-time (fn []
      ($/text $category current-category)
      ($/prepend ($ :body) $elem)
      ($/fade-in $elem)
      ($/fade-in $category)
      (.focus (first ($/find $elem :input)))
      (reset! transitioning? false)))))

(defn render-static-page [selector]
  (remove-active-koan)
  (let [$el ($ selector)
        $other ($ (first (set/difference #{"#welcome" "#the-end"} #{selector})))]
    ($/fade-out $other)
    (wait fadeout-time (fn []
      ($/fade-out ($ :.category))
      ($/fade-in $el)
      (reset! transitioning? false)))))

(defn render-current-koan []
  (cond
    (str/blank? (.-hash js/location))
       (render-static-page "#welcome")
    (= (:category (current-koan-index)) "complete")
      (render-static-page "#the-end")
    (meditations/koan-exists? (current-koan-index))
      (let [current-koan (meditations/koan-for-index (current-koan-index))]
        (render-koan current-koan))
    :else
      (update-location-hash)))

(defn resize-input [input]
  (let [$input ($ input)
        remove-spaces (fn [text] (str/replace text " " "_"))
        $parent ($/parent $input)
        $shadow ($/find $parent :.shadow)]
    ($/text $shadow (remove-spaces ($/val $input)))
    (let [shadow-width ($/width $shadow)
          input-width ($/width $input)]
      (cond
        (>= shadow-width input-width)
          ($/width $input (+ shadow-width (* 4 char-width)))
        (>= (- input-width (* 4 char-width)) shadow-width)
          ($/width $input (+ shadow-width (* 4 char-width)))))))

(defn show-error-message []
  (let [$code-box ($ :.code-box)]
    (if ($/has-class $code-box "incorrect")
      (let [$error ($ :.error)]
        ($/remove-class $code-box "incorrect")
        ($/fade-out $error)
        (wait 300 #(
          ($/add-class $code-box "incorrect")
          ($/fade-in $error)
          (reset! transitioning? false))))
      (let [$error ($ (error-message))]
        ($/add-class $code-box "incorrect")
        ($/after ($ :.code-box) $error)
        ($/fade-in $error)
        (reset! transitioning? false)))))

(defn evaluate-koan []
  (when (not @transitioning?)
    (let [input (input-string)]
      (reset! transitioning? true)
      (log "Evaluating " input)
      (let [result (eval-code input)]
        (log (clj->js result))
        (if (or (:error result) (not= (:value result) true))
          (show-error-message)
          (load-next-koan))))))

(defn handle-document-ready []
  (let [$doc ($ js/document)]
    ($/on $doc :click :.text #(.focus (first ($ :input))))
    ($/on $doc :keypress :input #(when (= (.-which %) enter-key) (evaluate-koan)))
    ($/on $doc :input :input #(resize-input (.-target %))))
  (render-current-koan))

(defn handle-hashchange []
  (render-current-koan))

(defn init []
  (eval-code "(require '[clojure.set :as set]) (require '[clojure.string :as str])")
  ($/document-ready handle-document-ready)
  (set! (.-onhashchange js/window) handle-hashchange))

(defn ^:export reload []
  (init))
