import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}
private val hasXcode15: Boolean by lazy {
    try {
        val process: Process = ProcessBuilder("xcodebuild", "-version").start()
        process.inputStream.bufferedReader().use { reader ->
            process.waitFor() == 0 && reader.readText().startsWith("Xcode 15.")
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        false
    }
}
val binConfig: org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit = {
    binaries {
        executable {
            entryPoint = "com.softartdev.sample.main"
        }
        all {
            if (hasXcode15) linkerOpts += "-ld64" //TODO: remove after update Kotlin >= 1.9.10
        }
    }
}
kotlin {
    iosX64("uikitX64", binConfig)
    iosArm64("uikitArm64", binConfig)
    iosSimulatorArm64("uikitSimulatorArm64", binConfig)
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":sample:shared"))
                implementation(project(":theme:theme-material"))
                implementation(project(":theme:theme-material3"))
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation("dev.icerock.moko:resources:${rootProject.extra["moko_resources_version"]}")
            }
        }
        val uikitX64Main by getting
        val uikitArm64Main by getting
        val uikitSimulatorArm64Main by getting
        val uikitMain by creating {
            dependsOn(commonMain)
            uikitX64Main.dependsOn(this)
            uikitArm64Main.dependsOn(this)
            uikitSimulatorArm64Main.dependsOn(this)
            dependencies {
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.sofartdev.sample"
}

tasks.withType<KotlinNativeCompile>().configureEach {
    dependsOn(":sample:ios:generateMRcommonMain")
}