import groovy.lang.GroovyObject
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig

plugins {
    java
    `maven-publish`
    war
    id("com.jfrog.artifactory") version "4.9.6"
}

group = "io.lucasvalenteds"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.glassfish.jersey.core", "jersey-server", properties["version.jersey"].toString())
    implementation("org.glassfish.jersey.containers", "jersey-container-jdk-http", properties["version.jersey"].toString())
    implementation("org.glassfish.jersey.containers", "jersey-container-servlet-core", properties["version.jersey"].toString())
    implementation("org.glassfish.jersey.inject", "jersey-hk2", properties["version.jersey"].toString())
    implementation("org.glassfish.jersey.media", "jersey-media-moxy", properties["version.jersey"].toString())
    implementation("org.glassfish.jersey.bundles", "jaxrs-ri", properties["version.jersey"].toString())
    implementation("javax.servlet", "javax.servlet-api", properties["version.servlet"].toString())

    testImplementation("org.junit.jupiter", "junit-jupiter", properties["version.junit"].toString())
}

configure<JavaPluginConvention> {
    targetCompatibility = JavaVersion.VERSION_11
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED)
    }
}

configure<ArtifactoryPluginConvention> {
    setContextUrl(project.findProperty("artifactory.url") ?: System.getenv("ARTIFACTORY_URL"))
    publish(delegateClosureOf<PublisherConfig> {
        repository(delegateClosureOf<GroovyObject> {
            setProperty("repoKey", project.findProperty("artifactory.repo") ?: System.getenv("ARTIFACTORY_REPO"))
            setProperty("username", project.findProperty("artifactory.username") ?: System.getenv("ARTIFACTORY_USERNAME"))
            setProperty("password", project.findProperty("artifactory.password") ?: System.getenv("ARTIFACTORY_PASSWORD"))
            setProperty("maven", true)
        })
        defaults(delegateClosureOf<GroovyObject> {
            invokeMethod("publications", "mavenJava")
        })
    })
}

configure<PublishingExtension> {
    repositories {
        maven {
            url = uri((project.findProperty("artifactory.url") ?: System.getenv("ARTIFACTORY_URL")).toString())
            credentials {
                username = (project.findProperty("artifactory.username") ?: System.getenv("ARTIFACTORY_USERNAME")).toString()
                password = (project.findProperty("artifactory.password") ?: System.getenv("ARTIFACTORY_PASSWORD")).toString()
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(file("build/libs/app-0.1.0.war"))
        }
    }
}

