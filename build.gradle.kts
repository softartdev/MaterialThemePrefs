buildscript {
    extra["moko_resources_version"] = "0.23.0"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.5.0-beta01")
        classpath("dev.icerock.moko:resources-generator:${rootProject.extra["moko_resources_version"]}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}