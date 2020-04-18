plugins {
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

dependencies {
    implementation("org.springframework", "spring-core", properties["version.spring"].toString())
    implementation("org.springframework", "spring-context", properties["version.spring"].toString())
    implementation("org.springframework", "spring-aop", properties["version.spring"].toString())
    testImplementation("org.springframework", "spring-test", properties["version.spring"].toString())
}

configure<ApplicationPluginConvention> {
    mainClassName = "io.lucasvalenteds.calculator.spring.Main"
}
