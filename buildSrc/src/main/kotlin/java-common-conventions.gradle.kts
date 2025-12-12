plugins {
    java
    checkstyle
    jacoco
}

group = "com.akimi"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Required for jacoco aggregation
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
    }
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

// Uncomment, if you need to see test coverage reports for subprojects.
// Currently, an aggregate report is enough.
//tasks.test {
//    // On all test tasks, create test report
//    finalizedBy(tasks.jacocoTestReport)
//}
//tasks.jacocoTestReport {
//    // Generate test coverage reports for subprojects
//    dependsOn(tasks.test)
//}
