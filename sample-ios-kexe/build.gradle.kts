plugins {
    kotlin("multiplatform")
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()
    val iosX64 = iosX64()
    configure(listOf(iosArm64, iosSimulatorArm64, iosX64)) {
        binaries {
            executable {
                entryPoint = "com.softartdev.sample.main"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("dev.icerock.moko:resources:${rootProject.extra["moko_resources_version"]}")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.sofartdev.sample"
}
