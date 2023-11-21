plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("convention.publication")
}
group = project.property("GROUP").toString()
version = project.property("VERSION").toString()

kotlin {
    jvmToolchain(11)
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain.dependencies {
            api(project(":theme:theme-prefs"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation("dev.icerock.moko:resources-compose:${rootProject.extra["moko_resources_version"]}")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
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
    namespace = "com.softartdev.theme.material"
}
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(tasks.withType<Sign>())
}