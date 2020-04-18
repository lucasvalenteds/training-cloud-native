FROM adoptopenjdk/openjdk8:alpine

WORKDIR /app

COPY build/libs/microservice-github-0.1.0.jar microservice-github.jar

EXPOSE 8081

CMD ["java", "-jar", "microservice-github.jar"]
