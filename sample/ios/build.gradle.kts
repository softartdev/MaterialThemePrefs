import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
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
    iosX64(binConfig)
    iosArm64(binConfig)
    iosSimulatorArm64(binConfig)
    sourceSets {
        commonMain.dependencies {
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
}

multiplatformResources {
    resourcesPackage.set("com.softartdev.sample")
}
