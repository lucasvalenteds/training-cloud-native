FROM adoptopenjdk/openjdk8:alpine

WORKDIR /app

COPY build/libs/microservice-twitter-0.1.0.jar microservice-twitter.jar

EXPOSE 8082

CMD ["java", "-jar", "microservice-twitter.jar"]
