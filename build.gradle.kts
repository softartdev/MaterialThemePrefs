buildscript {
    extra["kotlin_version"] = "2.0.0-RC3"
    extra["android_min_sdk_version"] = 21
    extra["android_compile_sdk_version"] = 34
    extra["jdk_version"] = 17
    extra["moko_resources_version"] = "0.24.0-beta-4"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin_version"]}")
        classpath("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${rootProject.extra["kotlin_version"]}")
        classpath("com.android.tools.build:gradle:8.4.0")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.6.10-rc03")
        classpath("dev.icerock.moko:resources-generator:${rootProject.extra["moko_resources_version"]}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}