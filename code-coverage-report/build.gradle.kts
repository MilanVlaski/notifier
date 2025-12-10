import org.gradle.internal.impldep.org.jsoup.nodes.Document

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

tasks.named<JacocoReport>("testCodeCoverageReport") {
    reports {
        xml.required.set(true)
        csv.required.set(true)
    }
}

tasks.check {
    dependsOn(tasks.named<JacocoReport>("testCodeCoverageReport")) // <.>
}
