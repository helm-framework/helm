(ns leiningen.new.helm
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "helm"))

(defn static [p]
  (slurp (str "resources/leiningen/new/helm/" p)))

(defn helm
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :db-pass "sWTX/.9LQA2Jw"
              :cookie-key "crGahfEs*He$i8fX"}]
    (main/info "Generating fresh 'lein new' helm project.")
    (->files data
      ["credentials.gpg" "credentials.gpg"]
      ["deploy.sh" (render "deploy.sh"  data)]
      ["docker-compose.yml" (render "docker-compose.yml" data)]
      ["dockerfiles/dev/Dockerfile" (render "dockerfiles/dev/Dockerfile" data)]
      ["dockerfiles/nginx/default.conf"
       (render "dockerfiles/nginx/default.conf" data)]
      ["dockerfiles/nginx/Dockerfile" (render "dockerfiles/nginx/Dockerfile" data)]
      ["dockerfiles/nginx/nginx.conf" (render "dockerfiles/nginx/nginx.conf" data)]
      ["dockerfiles/prod/Dockerfile" (render "dockerfiles/prod/Dockerfile" data)]
      [".gitignore" (render ".gitignore" data)]
      ["notes.md" (render "notes.md" data)]
      [".nrepl-port" (render ".nrepl-port" data)]
      ["production.yml" (render "production.yml" data)]
      ["project.clj" (render "project.clj" data)]
      ["resources/public/index.html" (render "resources/public/index.html" data)]
      ["resources/public/static/css/app.css" (render "resources/public/static/css/app.css" data)]
      ["resources/public/static/img/gitkeep" (render "resources/public/static/img/gitkeep" data)]
      "resources/public/static/js/"
      ["resources/public/static/vendor/bootstrap/css/bootstrap.css"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap.css")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap.css.map"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap.css.map")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap.min.css"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap.min.css")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap.min.css.map"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap.min.css.map")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap-theme.css"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap-theme.css")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap-theme.css.map"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap-theme.css.map")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap-theme.min.css"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap-theme.min.css")]
      ["resources/public/static/vendor/bootstrap/css/bootstrap-theme.min.css.map"
       (static "resources/public/static/vendor/bootstrap/css/bootstrap-theme.min.css.map")]
      ["resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.eot"
       (static "resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.eot")]
      ["resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.svg"
       (static "resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.svg")]
      ["resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.ttf"
       (static "resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.ttf")]
      ["resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.woff"
       (static "resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.woff")]
      ["resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.woff2"
       (static "resources/public/static/vendor/bootstrap/fonts/glyphicons-halflings-regular.woff2")]
      ["resources/public/static/vendor/bootstrap/js/bootstrap.js"
       (static "resources/public/static/vendor/bootstrap/js/bootstrap.js")]
      ["resources/public/static/vendor/bootstrap/js/bootstrap.min.js"
       (static "resources/public/static/vendor/bootstrap/js/bootstrap.min.js")]
      ["resources/public/static/vendor/bootstrap/js/npm.js"
       (static "resources/public/static/vendor/bootstrap/js/npm.js")]
      ["src/clj/{{sanitized}}/db.clj" (render "src/clj/helm/db.clj" data)]
      ["src/clj/{{sanitized}}/init.clj" (render "src/clj/helm/init.clj" data)]
      ["src/clj/{{sanitized}}/queries.sql" (render "src/clj/helm/queries.sql" data)]
      ["src/clj/{{sanitized}}/routes.clj" (render "src/clj/helm/routes.clj" data)]
      ["src/clj/{{sanitized}}/server.clj" (render "src/clj/helm/server.clj" data)]
      ["src/clj/{{sanitized}}/srvc/mailgun.clj" (render "src/clj/helm/srvc/mailgun.clj" data)]
      ["src/clj/{{sanitized}}/srvc/stripe.clj" (render "src/clj/helm/srvc/stripe.clj" data)]
      ["src/clj/{{sanitized}}/synchronize.clj" (render "src/clj/helm/synchronize.clj" data)]
      ["src/clj/{{sanitized}}/util.clj" (render "src/clj/helm/util.clj" data)]
      ["src/cljs/{{sanitized}}/core.cljs" (render "src/cljs/helm/core.cljs" data)]
      ["src/cljs/{{sanitized}}/routes.cljs" (render "src/cljs/helm/routes.cljs" data)]
      ["src/cljs/{{sanitized}}/state.cljs" (render "src/cljs/helm/state.cljs" data)]
      ["src/cljs/{{sanitized}}/util.cljs" (render "src/cljs/helm/util.cljs" data)])))
