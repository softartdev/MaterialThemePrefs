import org.jetbrains.compose.ComposeBuildConfig.composeVersion
import org.jetbrains.compose.compose

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
    android {
        publishLibraryVariants("release", "debug")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation("dev.icerock.moko:resources:${rootProject.extra["moko_resources_version"]}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.preference:preference:1.2.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopTest by getting
    }
}
android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
multiplatformResources {
    multiplatformResourcesPackage = "com.softartdev.themepref"
}