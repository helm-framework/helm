#!/bin/bash

sudo chown jordan:jordan -R *; && \
lein do clean, uberjar; && \
rm -rf dockerfiles/nginx/static; && \
cp -r resources/public/static dockerfiles/nginx/static; && \
mkdir -p dockerfiles/nginx/static/js/out/; && \
cp -r out/app.js dockerfiles/nginx/static/js/out/; && \
cp -r out/app.map.js dockerfiles/nginx/static/js/out/; && \
cp -r target/app.jar dockerfiles/prod/; && \
eval (docker-machine env {{name}}); && \
docker-compose -f production.yml build; && \
docker-compose -f production.yml up -d;
