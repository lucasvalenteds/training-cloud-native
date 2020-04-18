plugins {
    application
    java
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

dependencies {
    implementation(project(":toll-domain"))

    implementation("com.sun.activation", "javax.activation", properties["version.javax.activation"].toString())

    implementation("org.apache.cxf", "apache-cxf", properties["version.cxf"].toString())
    implementation("org.apache.cxf", "cxf-rt-frontend-jaxrs", properties["version.cxf"].toString())
    implementation("org.apache.cxf", "cxf-rt-rs-extension-providers", properties["version.cxf"].toString())
    implementation("org.apache.cxf", "cxf-rt-rs-client", properties["version.cxf"].toString())
    implementation("org.apache.cxf", "cxf-integration-cdi", properties["version.cxf"].toString())

    implementation("com.fasterxml.jackson.jaxrs", "jackson-jaxrs-json-provider", properties["version.jackson"].toString())
    implementation("javax.json", "javax.json-api", properties["version.json"].toString())

    implementation("org.springframework", "spring-core", properties["version.spring"].toString())
    implementation("org.springframework", "spring-context", properties["version.spring"].toString())
    implementation("org.springframework", "spring-aop", properties["version.spring"].toString())
    implementation("org.springframework", "spring-beans", properties["version.spring"].toString())
    implementation("org.springframework", "spring-web", properties["version.spring"].toString())
    testImplementation("org.springframework", "spring-test", properties["version.spring"].toString())

    implementation("org.eclipse.jetty", "jetty-server", properties["version.jetty"].toString())
    implementation("org.eclipse.jetty", "jetty-annotations", properties["version.jetty"].toString())
    implementation("org.eclipse.jetty", "jetty-webapp", properties["version.jetty"].toString())
}

configure<ApplicationPluginConvention> {
    mainClassName = "io.lucasvalenteds.toll.web.Main"
}