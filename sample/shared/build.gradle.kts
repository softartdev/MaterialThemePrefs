import com.android.build.gradle.internal.lint.AndroidLintAnalysisTask
import com.android.build.gradle.internal.lint.LintModelWriterTask

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

kotlin {
    jvmToolchain(rootProject.extra["jdk_version"] as Int)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "${rootProject.extra["jdk_version"] as Int}"
        }
    }
    androidTarget()
//    iosX64()
//    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain.dependencies {
            api(project(":theme:theme-material"))
            api(project(":theme:theme-material3"))
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
        sourceCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
        targetCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
    }
    namespace = "com.softartdev.shared"
}
tasks.withType<AndroidLintAnalysisTask>{
    dependsOn("generateResourceAccessorsForAndroidUnitTest")
}
tasks.withType<LintModelWriterTask>{
    dependsOn("generateResourceAccessorsForAndroidUnitTest")
}
