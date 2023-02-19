import org.gradle.jvm.tasks.Jar

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
                implementation(compose.material)
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
        val androidInstrumentedTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val desktopTest by getting
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
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    namespace = "com.softartdev.themepref"
}
multiplatformResources {
    multiplatformResourcesPackage = "com.softartdev.themepref"
}
//TODO try to remove after update moko-resources version > 0.20.1
val generateMRAction: Action<Task> = Action {
    dependsOn(":material-theme-prefs:generateMRcommonMain")
    dependsOn(":material-theme-prefs:generateMRdesktopMain")
    dependsOn(":material-theme-prefs:generateMRandroidMain")
    dependsOn(":material-theme-prefs:generateMRiosX64Main")
    dependsOn(":material-theme-prefs:generateMRiosSimulatorArm64Main")
    dependsOn(":material-theme-prefs:generateMRiosArm64Main")
}
sequenceOf(ProcessResources::class, Jar::class, Sign::class).forEach {
    tasks.withType(it.java, generateMRAction)
}
val signAction: Action<Task> = Action {
    dependsOn(":material-theme-prefs:signKotlinMultiplatformPublication")
    dependsOn(":material-theme-prefs:signDesktopPublication")
    dependsOn(":material-theme-prefs:signAndroidDebugPublication")
    dependsOn(":material-theme-prefs:signAndroidReleasePublication")
    dependsOn(":material-theme-prefs:signIosX64Publication")
    dependsOn(":material-theme-prefs:signIosSimulatorArm64Publication")
    dependsOn(":material-theme-prefs:signIosArm64Publication")
}
tasks.withType(PublishToMavenLocal::class.java, signAction)
sequenceOf(
    "publishAllPublicationsToSonatypeRepository",
    "publishKotlinMultiplatformPublicationToSonatypeRepository",
    "publishAndroidDebugPublicationToSonatypeRepository",
    "publishAndroidReleasePublicationToSonatypeRepository",
    "publishDesktopPublicationToSonatypeRepository",
    "publishIosX64PublicationToSonatypeRepository",
    "publishIosSimulatorArm64PublicationToSonatypeRepository",
    "publishIosArm64PublicationToSonatypeRepository"
).map(tasks::findByName)
    .filterNotNull()
    .forEach(signAction::execute)