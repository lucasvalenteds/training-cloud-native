# Jenkins

This project demonstrates how to deliver a Web application via CI/CD pipeline.

## How to run

### Infrastructure

| Description | Folder | Command |
| :--- | :--- | :--- |
| Provision infrastructure | `./ansible` | `vagrant up && vagrant provision` |
| Build Docker image | `./` | `packer build packer.json` |

### Application

| Description | Folder | Command |
| :--- | :--- | :--- |
| Run tests | `./` | `./gradlew test` |

## How to configure

| Credential ID | Example |
| :--- | :--- |
| `artifactory-url` |`http://192.168.100.12:8081/artifactory` |
| `artifactory-repo` | `app-repo` |
| `artifactory-username` | `admin` |
| `artifactory-password` | `password` |
| `application-war-url` | `http://192.168.100.12:8081/artifactory/app-repo/io/lucasvalenteds/app/0.1.0/app-0.1.0.war` |
| `docker-hub-username` | `johnsmith` |
| `docker-hub-password` | `notpassword` |

> Note: The SSH key to GitHub also need to be set on job creation.
