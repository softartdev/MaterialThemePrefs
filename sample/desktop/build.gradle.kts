@file:Suppress("OPT_IN_USAGE")

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "com.softartdev"
version = "1.0"

kotlin {
    jvmToolchain(rootProject.extra["jdk_version"] as Int)
    jvm {
        compilerOptions.jvmTarget = JvmTarget.fromTarget("${rootProject.extra["jdk_version"]}")
//        withJava()
    }
    sourceSets {
        jvmMain.dependencies {
            implementation(project(":sample:shared"))
            implementation(compose.desktop.currentOs)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.softartdev.shared.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}