import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.springframework.boot") version "2.1.5.RELEASE"
}

group = "io.lucasvalenteds.microservices"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-web", properties["version.spring"].toString()) {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot", "spring-boot-starter-log4j2", properties["version.spring"].toString())
    testImplementation("org.springframework.boot", "spring-boot-starter-test", properties["version.spring"].toString())
    runtime("org.springframework.boot", "spring-boot-starter-tomcat")

    implementation("org.springframework.social", "spring-social-twitter", properties["version.spring.social"].toString())

    testImplementation("org.mockito", "mockito-core", properties["version.mockito"].toString())
    testImplementation("org.junit.jupiter", "junit-jupiter", properties["version.junit"].toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-params", properties["version.junit"].toString())
}

configure<JavaPluginConvention> {
    targetCompatibility = JavaVersion.VERSION_11
    sourceCompatibility = JavaVersion.VERSION_11
}

configurations {
    all {
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED)
    }
}

