# Jetty

Application that responds HTTP requests hosted on Jetty.

## How to run

| Description | Command |
| :--- | :--- |
| Run tests | `./gradlew test` |
| Generate WAR file with application | `./gradlew war` |
| Copy WAR file to Jetty folder | `cp jetty-server/build/libs/jetty-server.war <jetty_folder>/webapps` |
| Start Jetty server | `./<jetty_folder>/bin/jetty.sh run` |
