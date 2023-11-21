plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
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
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation("dev.icerock.moko:resources:${rootProject.extra["moko_resources_version"]}")
            implementation("dev.icerock.moko:resources-compose:${rootProject.extra["moko_resources_version"]}")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        androidMain.get().dependsOn(commonMain.get())//TODO remove after update moko-resources > 0.23.0
    }
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig.minSdk = rootProject.extra["android_min_sdk_version"] as Int
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(layout.buildDirectory.get().asFile, "generated/moko/androidMain/res"))
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.softartdev.theme.pref"
}
multiplatformResources {
    multiplatformResourcesPackage = "com.softartdev.theme.pref"
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(tasks.withType<Sign>())
}
tasks.configureEach {
    when (name) {
        "androidDebugSourcesJar" -> dependsOn(tasks.named("generateMRandroidMain"))
        "androidReleaseSourcesJar" -> dependsOn(tasks.named("generateMRandroidMain"))
        "iosArm64SourcesJar" -> dependsOn(tasks.named("generateMRiosArm64Main"))
        "iosSimulatorArm64SourcesJar" -> dependsOn(tasks.named("generateMRiosSimulatorArm64Main"))
        "iosX64SourcesJar" -> dependsOn(tasks.named("generateMRiosX64Main"))
    }
}