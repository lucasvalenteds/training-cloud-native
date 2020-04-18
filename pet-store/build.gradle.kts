import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

group = "io.lucasvalenteds.petstore"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.inject", "guice", properties["version.guice"].toString())
    testImplementation("org.junit.jupiter", "junit-jupiter", properties["version.junit"].toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-params", properties["version.junit"].toString())
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(TestLogEvent.PASSED, TestLogEvent.FAILED)
    }
}
