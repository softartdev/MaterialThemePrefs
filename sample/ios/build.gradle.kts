plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    listOf(
//        iosX64(),
//        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        iosMain.dependencies {
            implementation(projects.sample.shared)
            implementation(libs.compose.ui)
        }
    }
}

// Kotlin/Native release framework linking can stall for this sample setup; keep debug framework in the default build lifecycle.
tasks.matching { it.name.startsWith("linkReleaseFrameworkIos") }.configureEach {
    enabled = false
}
