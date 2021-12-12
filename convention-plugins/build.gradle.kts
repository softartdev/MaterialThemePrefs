plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:0.22.0")
}