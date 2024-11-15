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
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.components.resources)
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        androidMain.dependencies {
            api(compose.ui)
        }
    }
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig.minSdk = rootProject.extra["android_min_sdk_version"] as Int
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(layout.buildDirectory.get().asFile, "generated/moko/androidMain/res"))
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
        targetCompatibility = JavaVersion.toVersion(rootProject.extra["jdk_version"] as Int)
    }
    namespace = "com.softartdev.theme.pref"
}
compose.resources {
    publicResClass = true
}
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(tasks.withType<Sign>())
}