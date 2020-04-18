# Microservices

This projects demonstrates the implementation of a microservices architecture.

It provides two APIs that provides data from social media platforms and one API that aggregates data from those microservices.

Each microservice is located in a separate folder and can be deployed individually.

## How to run

| Description | Command |
| :--- | :--- |
| Run automated tests | `./gradlew test` |
| Generate JAR file to be deployed | `gradle bootJar` |

## How to deploy

| Description | Command |
| :--- | :--- |
| Deploy all microservices at once | `docker-compose up --detach` |
| Check logs | `docker-compose logs --follow` |
| Stop one specific microservice | `docker-compose stop <microservice name>` |

> ProTip: Run `./jar.sh` to generate all JAR files

## API keys

### GitHub

The API is public, so there is not necessary to provide any private key.

### Twitter

1. Go to [Twitter Developer page](https://developer.twitter.com/en/apps) and create an app
2. Create file `src/main/resources/twitter.properties` with the keys below

```properties
twitter.consumerKey=
twitter.consumerSecret=
```
