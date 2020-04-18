FROM ubuntu:18.04

RUN apt update && apt install openjdk-11-jre jetty9 --yes --no-install-recommends

COPY application.war /usr/share/jetty9/webapps

EXPOSE 8080

WORKDIR /usr/share/jetty9

CMD ["./bin/jetty.sh", "run"]
