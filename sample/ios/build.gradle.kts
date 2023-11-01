import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

val binConfig: org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit = {
    binaries {
        executable {
            entryPoint = "com.softartdev.sample.main"
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