plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

kotlin {
    jvmToolchain(11)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain.dependencies {
            implementation(project(":theme:theme-prefs"))
            implementation(project(":theme:theme-material"))
            implementation(project(":theme:theme-material3"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation("dev.icerock.moko:resources-compose:${rootProject.extra["moko_resources_version"]}")
        }
        jvmMain.dependencies {
            implementation(compose.preview)
            implementation(compose.uiTooling)
        }
    }
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig.minSdk = rootProject.extra["android_min_sdk_version"] as Int
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.softartdev.shared"
}