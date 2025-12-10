plugins {
    checkstyle
}

subprojects {
    plugins.withId("java") {
        apply(plugin = "checkstyle")
    }
}