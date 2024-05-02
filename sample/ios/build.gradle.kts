import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinNativeCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("dev.icerock.mobile.multiplatform-resources")
}

val binConfig: KotlinNativeTarget.() -> Unit = {
    binaries {
        executable {
            entryPoint = "com.softartdev.sample.main"
        }
    }
}
kotlin {
//    iosX64(binConfig)
//    iosArm64(binConfig)
    iosSimulatorArm64(binConfig)
    sourceSets {
        commonMain.dependencies {
            implementation(project(":sample:shared"))
        }
    }
}

multiplatformResources {
    resourcesPackage.set("com.sofartdev.sample")
}

tasks.withType<KotlinNativeCompile>().configureEach {
    dependsOn(":sample:ios:generateMRcommonMain")
}