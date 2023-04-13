plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}
android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.softartdev.sample"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.softartdev.sample"
}
dependencies {
    implementation(project(":sample-shared"))
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
}