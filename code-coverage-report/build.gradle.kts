plugins {
    base
    id("jacoco-report-aggregation")
}

dependencies {
    jacocoAggregation(project(":web"))
}

repositories {
    mavenCentral()
}

reporting {
    reports {
        val testCodeCoverageReport by creating(JacocoCoverageReport::class) {
            testSuiteName = "test"
        }
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) // <.>
}
