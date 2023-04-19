buildscript {
    extra["moko_resources_version"] = "0.21.2"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.4.0")
        classpath("dev.icerock.moko:resources-generator:${rootProject.extra["moko_resources_version"]}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}