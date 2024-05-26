import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.softartdev"
version = "1.0"

kotlin {
    jvmToolchain(rootProject.extra["jdk_version"] as Int)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "${rootProject.extra["jdk_version"] as Int}"
        }
        withJava()
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