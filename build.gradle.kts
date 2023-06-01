import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version System.getProperty("spring_version")
	id("io.spring.dependency-management") version System.getProperty("spring_dm_version")
	kotlin("jvm") version System.getProperty("kotlin_version")
	kotlin("plugin.spring") version System.getProperty("kotlin_version")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_19
	targetCompatibility = JavaVersion.VERSION_19
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	implementation("io.github.oshai:kotlin-logging-jvm:4.0.0-beta-27")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "19"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
