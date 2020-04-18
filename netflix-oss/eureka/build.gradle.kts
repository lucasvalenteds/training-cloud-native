import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "Greenwich.SR1"

dependencies {
	implementation("org.glassfish.jaxb:jaxb-runtime")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}

configure<DependencyManagementExtension> {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${extra["springCloudVersion"]}")
	}
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}
