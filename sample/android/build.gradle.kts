plugins {
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.android.application")
    kotlin("android")
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig {
        applicationId = "com.softartdev.sample"
        minSdk = rootProject.extra["android_min_sdk_version"] as Int
        targetSdk = compileSdk
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
        targetCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
    }
    kotlinOptions.jvmTarget = "${rootProject.extra["jdk_version"] as Int}"
    namespace = "com.softartdev.sample"
}
dependencies {
    implementation(project(":sample:shared"))
    implementation("androidx.activity:activity-compose:1.9.0")
}