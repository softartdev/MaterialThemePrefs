plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}
android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.softartdev.sample"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    namespace = "com.softartdev.sample"
}
dependencies {
    implementation(project(":sample-shared"))
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("androidx.appcompat:appcompat:1.4.2")
}