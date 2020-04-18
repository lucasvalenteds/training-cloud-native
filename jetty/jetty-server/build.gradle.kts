plugins {
    java
    war
}

dependencies {
    implementation(project(":jetty-app"))
    implementation("org.springframework", "spring-core", properties["version.spring"].toString())
    implementation("org.springframework", "spring-context", properties["version.spring"].toString())
    implementation("org.springframework", "spring-aop", properties["version.spring"].toString())
    implementation("org.springframework", "spring-webmvc", properties["version.spring"].toString())
    implementation("javax.servlet", "javax.servlet-api", properties["version.servlet"].toString())
    implementation("com.fasterxml.jackson.core", "jackson-databind", properties["version.jackson"].toString())
    testImplementation("org.springframework", "spring-test", properties["version.spring"].toString())
    testImplementation("org.mockito", "mockito-core", properties["version.mockito"].toString())
}
