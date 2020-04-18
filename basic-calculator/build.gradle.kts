import org.gradle.api.tasks.testing.logging.TestLogEvent;

plugins {
    java
    application
}

group = "io.lucasvalenteds.calculator"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation("org.springframework", "spring-core", properties["version.spring"].toString())
    implementation("org.springframework", "spring-context", properties["version.spring"].toString())
    implementation("org.springframework", "spring-aop", properties["version.spring"].toString())
    testImplementation("org.springframework", "spring-test", properties["version.spring"].toString())

    testImplementation("org.junit.jupiter", "junit-jupiter", properties["version.junit"].toString())
    testImplementation("org.junit.jupiter", "junit-jupiter-params", properties["version.junit"].toString())
}

configure<ApplicationPluginConvention> {
    mainClassName = "io.lucasvalenteds.calculator.spring.Main"
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
