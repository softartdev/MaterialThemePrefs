plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.vanniktech.gradle.maven.publish.plugin)
}
