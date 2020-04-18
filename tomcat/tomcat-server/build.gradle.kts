plugins {
    java
    war
}

dependencies {
    implementation(project(":tomcat-app"))
    implementation("com.google.inject.extensions", "guice-servlet", properties["version.guice"].toString())
    implementation("javax.servlet", "javax.servlet-api", properties["version.servlet"].toString())
    implementation("com.fasterxml.jackson.core", "jackson-databind", properties["version.jackson"].toString())
    testImplementation("org.mockito", "mockito-core", properties["version.mockito"].toString())
}
