# Tomcat

Application that responds HTTP requests hosted on Tomcat.

## How to run

| Description | Command |
| :--- | :--- |
| Run tests | `./gradlew test` |
| Generate WAR file with application | `./gradlew war` |
| Copy WAR file to Tomcat folder | `cp tomcat-server/build/libs/tomcat-server.war <tomcat_folder>/webapps` |
| Start Tomcat server | `./<tomcat_folder>/bin/catalina.sh run` |
