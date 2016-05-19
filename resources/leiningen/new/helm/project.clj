(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj" "src/cljs"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122" :scope "provided"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "1.0.0-alpha22"]
                 [cljs-http "0.1.40"]
                 [org.immutant/web "2.1.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-defaults "0.1.4"]
                 [conman "0.4.7"]
                 [mount "0.1.10"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [bidi "1.23.1"]
                 [clj-http "2.1.0"]
                 [org.clojure/tools.logging "0.3.1" :exclusions [org.clojure/clojure]]
                 ;; [stencil "0.5.0"] If you need mustache templating
                 ;; [cheshire "5.6.1"] If you need JSON handling
                 ;; [me.raynes/cegdown "0.1.1"] For markdown
                 ]

  :plugins [[lein-cljsbuild "1.0.5"]]

  :min-lein-version "2.5.0"
  :uberjar-name "app.jar"

  :cljsbuild
  {:builds
   {:app
    {:source-paths ["src/cljs"]
     :compiler {:output-to "resources/public/static/js/out/app.js"
                :output-dir "resources/public/static/js/out"
                :source-map "resources/public/static/js/out/app.map.js"
                :cache-analysis true
                :source-map-timestamp true
                :optimizations :none
                :pretty-print true
                :asset-path "/static/js/out"
                :main {{name}}.core}}}}
  :clean-targets ^{:protect false} [:target-path "out" "resources/public/static/js"]
  :figwheel {:css-dirs ["resources/public/static/css"]
             :open-file-command "emacsclient"}

  :profiles {:dev {:dependencies [[ring/ring-devel "1.4.0"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [org.clojure/tools.trace "0.7.9"]]
                   :plugins [[lein-figwheel "0.5.2"]
                             [refactor-nrepl "2.2.0"]
                             [cider/cider-nrepl "0.12.0-SNAPSHOT"]]
                   :repl-options {:init-ns cal.server :port 4001 :host "0.0.0.0"}
                   :cljsbuild {:builds {:app {:figwheel true}}}}

             :uberjar {:hooks [leiningen.cljsbuild]
                       :env {:production true}
                       :aot :all
                       :uberjar-exclusions [#"goog"]
                       :main cal.server
                       :cljsbuild {:builds {:app
                                            {:compiler
                                             {:optimizations :advanced
                                              :output-dir "out"
                                              :output-to "out/app.js"
                                              :source-map "out/app.map.js"
                                              :pretty-print false}}}}}})
