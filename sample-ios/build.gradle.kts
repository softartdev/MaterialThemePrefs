import org.jetbrains.compose.experimental.dsl.IOSDevices
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    iosX64("uikitX64") {
        binaries {
            executable {
                entryPoint = "com.softartdev.shared.main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":material-theme-prefs"))
                implementation(project(":sample-shared"))
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.softartdev.shared"
        projectName = "MaterialThemePrefs"

        deployConfigurations {
            simulator("IPhone12Pro") {
                //Usage: ./gradlew iosDeployIPhone12ProDebug
                device = IOSDevices.IPHONE_13_PRO
            }
        }
    }
}

kotlin {
    targets.withType<KotlinNativeTarget> {
        binaries.all {
            // TODO: the current compose binary surprises LLVM, so disable checks for now.
            freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
        }
    }
}
/*
kotlin.targets.withType<KotlinNativeTarget> {
    binaries.all {
        binaryOptions["memoryModel"] = "experimental"
        binaryOptions["freezing"] = "disabled"
    }
}
*/
