(ns sci-koans.main
  (:require
    [clojure.string :as str]
    [promesa.core :as p]
    ;; All the configs
    sci.configs.applied-science.js-interop
    sci.configs.cljs.pprint
    sci.configs.cljs.test
    sci.configs.funcool.promesa
    sci.configs.hoplon.javelin
    sci.configs.hoplon.hoplon
    hoplon.dom
    [sci.core :as sci]
    [sci.ctx-store :as store]))

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
   (try (sci/eval-string* sci-ctx code)
     (catch :default e
       (try (js/console.log "Evaluation failed:" (ex-message e)
              (some-> e ex-data clj->js))
         (catch :default _))
       {::error (str (.-message e)) :data (ex-data e)}))))

(defn eval-all [on-result code]
  (on-result (some->> code eval-code))
  true)

(defn list-libraries [all-config-vars]
  (->> all-config-vars
    (map (comp name :ns meta))
    (map #(clojure.string/replace % #"^sci\.configs\.[\w-]+\." ""))
    (remove #{"pprint" "test"})
    sort
    (str/join ", ")))

(defn gist-json->code [json]
  (->> json
    .-files
    js/Object.values
    seq
    (map (fn [o] (js->clj o :keywordize-keys true)))
    (filter (comp #{"Clojure"} :language)) ; incl. clj, cljs, cljc
    (sort-by :filename) ; we started with a map, which has no natural order
    (map #(do (assert (not (:truncated %)) "Can't handle truncated files")
            (str ";; " (:filename %) "\n" (:content %))))
    (str/join "\n;;---\n")))

(defn async-fetch-gist [gist-id]
  (p/let [resp (js/fetch (str "https://api.github.com/gists/" gist-id)
                 {:headers {"Accept" "application/vnd.github+json"
                            "X-GitHub-Api-Version" "2022-11-28"}})
          _ (when-not (.-ok resp) (throw (ex-info (str "Bad HTTP status "
                                                    (.-status resp) " "
                                                    (.-statusText resp))
                                           {})))
          json (.json resp)
          code (gist-json->code json)]
    (if (seq code)
      code
      "; No Clojure code found in the gist.")))

(defn ^:export init []
  (let [libs-el (js/document.getElementById "libs")]
    (set! (.-textContent libs-el) (list-libraries all-configs)))
  (js/console.log (eval-code "
                    (require '[clojure.set :as set])
                    (defn x [a] (str a a))
                    (x
                     (keyword \"a\"))
                    (set/union #{:a} #{:b})"))
  (println "Init run"))

(defn ^:export reload []
  (init)
  (println "Reload run (noop)"))
