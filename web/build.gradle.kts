plugins {
    id("java-common-conventions")
    // TODO hardcoded version
    id("org.springframework.boot") version "4.0.0"
}

description = "Message "

dependencies {
    // TODO hardcoded version
    implementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.0"));

    implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	testImplementation("org.springframework.boot:spring-boot-starter-security-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
}
