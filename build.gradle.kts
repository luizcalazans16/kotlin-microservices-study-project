plugins {
	java
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

allprojects {

	apply {
		plugin("java")
		plugin("io.spring.dependency-management")
	}

	group = "com.microservices.demo"
	version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_11

	repositories {
		mavenCentral()
	}

	dependencyManagement {
		imports {
			mavenBom("org.springframework.boot:spring-boot-dependencies:2.1.0.RELEASE")
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
		implementation("org.twitter4j:twitter4j-stream:4.0.7")
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		testImplementation("org.springframework.boot:spring-boot-starter-test")

	}
}

dependencies {
	subprojects.forEach {
		bootArchives(it)
	}
}


