FROM develar/java

MAINTAINER Jordan Schatz <jordan@noionlabs.com>

WORKDIR /app
ADD app.jar .

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/urandom"]

CMD ["-jar", "/app/app.jar"]
