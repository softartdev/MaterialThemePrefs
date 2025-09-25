@file:OptIn(ExperimentalWasmDsl::class)

import com.android.build.gradle.internal.lint.AndroidLintAnalysisTask
import com.android.build.gradle.internal.lint.LintModelWriterTask
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.android.library")
    id("convention.publication")
}
group = project.property("GROUP").toString()
version = project.property("VERSION").toString()

kotlin {
    jvmToolchain(rootProject.extra["jdk_version"] as Int)
    jvm("desktop") {
        compilerOptions.jvmTarget = JvmTarget.fromTarget("${rootProject.extra["jdk_version"]}")
    }
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    wasmJs {
        browser()
        binaries.executable()
    }
    sourceSets {
        commonMain.dependencies {
            api(project(":theme:theme-prefs"))
            api(compose.foundation)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation("androidx.compose.ui:ui-tooling:1.9.2")
        }
    }
    explicitApi()
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig.minSdk = rootProject.extra["android_min_sdk_version"] as Int
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
        targetCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
    }
    namespace = "com.softartdev.theme.material3"
}
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(tasks.withType<Sign>())
}
tasks.withType<AndroidLintAnalysisTask>{
    dependsOn("generateResourceAccessorsForAndroidUnitTest")
}
tasks.withType<LintModelWriterTask>{
    dependsOn("generateResourceAccessorsForAndroidUnitTest")
}
