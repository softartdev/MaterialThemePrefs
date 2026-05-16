plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.softartdev.sample"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.softartdev.sample"
        minSdk = 23
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jdk.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jdk.get())
    }
    buildFeatures.compose = true
    lint.disable += "Instantiatable"
}

dependencies {
    implementation(projects.sample.shared)
    implementation(libs.androidx.activity.compose)
}
