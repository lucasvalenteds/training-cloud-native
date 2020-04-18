plugins {
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

repositories {
    maven(url = "https://dl.bintray.com/reactivex/RxJava")
}

dependencies {
    implementation(project(":karyon-app"))

    implementation("javax.annotation", "javax.annotation-api", properties["version.javax.annotation"].toString())

    implementation("org.springframework", "spring-core", properties["version.spring"].toString())
    implementation("org.springframework", "spring-context", properties["version.spring"].toString())
    testImplementation("org.springframework", "spring-test", properties["version.spring"].toString())
    testImplementation("org.mockito", "mockito-core", properties["version.mockito"].toString())

    implementation("com.netflix.karyon", "karyon2-core", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-governator", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-admin-web", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-jersey-blocking", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-eureka", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-archaius", properties["version.karyon"].toString())
    implementation("com.netflix.karyon", "karyon2-servo", properties["version.karyon"].toString())

    implementation("io.reactivex", "rxjava", properties["version.rxjava"].toString())
    implementation("io.reactivex", "rxnetty", properties["version.rxnetty"].toString())
    implementation("io.netty", "netty-codec-http", properties["version.netty"].toString())
    implementation("com.netflix.governator", "governator", properties["version.governator"].toString())
    implementation("com.google.inject", "guice", properties["version.guice"].toString())

    implementation("com.fasterxml.jackson.core", "jackson-databind", properties["version.jackson"].toString())
}

configure<ApplicationPluginConvention> {
    mainClassName = "io.lucasvalenteds.calculator.web.Application"
}
