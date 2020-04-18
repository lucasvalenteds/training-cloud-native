plugins {
	application
    java
	id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "io.lucasvalenteds"
version = "0.1.0"

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
    implementation("javax.annotation", "javax.annotation-api", properties["version.javax.annotation"].toString())

    implementation("ch.qos.logback", "logback-classic", properties["version.logback"].toString())
	implementation("org.slf4j", "slf4j-log4j12", properties["version.slf4j"].toString())
	implementation("org.slf4j", "slf4j-jdk14", properties["version.slf4j"].toString())

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
	implementation("com.sun.jersey", "jersey-client", properties["version.jersey"].toString())
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

configure<ApplicationPluginConvention> {
	mainClassName = "io.lucasvalenteds.netflixoss.Application"
}
