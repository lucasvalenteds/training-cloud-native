import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
}

group = "io.lucasvalenteds.calculator"
version = "0.1.0"

subprojects {
    apply {
        plugin("java")
    }
    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter", "junit-jupiter", properties["version.junit"].toString())
        testImplementation("org.junit.jupiter", "junit-jupiter-params", properties["version.junit"].toString())
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
}
