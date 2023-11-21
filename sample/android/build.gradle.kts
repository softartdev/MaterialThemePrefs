plugins {
    id("org.jetbrains.compose")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    namespace = "com.softartdev.sample"
}
dependencies {
    implementation(project(":sample:shared"))
    implementation("androidx.activity:activity-compose:1.8.1")
}