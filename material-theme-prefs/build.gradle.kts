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
    android {
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation("dev.icerock.moko:resources:${rootProject.extra["moko_resources_version"]}")
                implementation("dev.icerock.moko:resources-compose:${rootProject.extra["moko_resources_version"]}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("androidx.preference:preference:1.2.1")
            }
        }
        val androidInstrumentedTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(compose.preview)
            }
        }
        val desktopTest by getting {
            dependsOn(commonTest)
            dependencies {
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
            dependencies {
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
android {
    compileSdk = rootProject.extra["android_compile_sdk_version"] as Int
    defaultConfig.minSdk = rootProject.extra["android_min_sdk_version"] as Int
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.softartdev.themepref"
}
multiplatformResources {
    multiplatformResourcesPackage = "com.softartdev.themepref"
}

tasks.withType<PublishToMavenRepository>().configureEach {
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