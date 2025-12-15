plugins {
    id("java-common-conventions")
    alias(libs.plugins.spring.boot)
}

description = "Notifier web app."

dependencies {
    // Recommended over Spring Dependency management by the creator, see: https://www.linen.dev/s/gradle-community/t/2579116/what-is-the-proper-way-to-apply-a-bom-in-a-library-project-i#a7d4a60a-ab60-48dc-8477-f3f4462d1e6a
    implementation(platform(libs.spring.boot.deps));
    implementation(project(":api"))

    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.boot:spring-boot-starter-mail")

    testImplementation("org.springframework.boot:spring-boot-starter-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.testcontainers:junit-jupiter:1.20.2")
    testImplementation("org.testcontainers:testcontainers:1.20.2")
}
