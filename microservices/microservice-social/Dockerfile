FROM adoptopenjdk/openjdk8:alpine

WORKDIR /app

COPY build/libs/microservice-social-0.1.0.jar microservice-social.jar

EXPOSE 8083

CMD ["java", "-jar", "microservice-social.jar"]
