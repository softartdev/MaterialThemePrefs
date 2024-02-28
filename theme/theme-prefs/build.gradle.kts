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
    jvmToolchain(rootProject.extra["jdk_version"] as Int)
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "${rootProject.extra["jdk_version"] as Int}"
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
multiplatformResources {
    resourcesPackage.set("com.softartdev.theme.pref")
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