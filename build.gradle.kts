buildscript {
    extra["android_min_sdk_version"] = 21
    extra["android_compile_sdk_version"] = 34
    extra["moko_resources_version"] = "0.23.0"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.5.10")
        classpath("dev.icerock.moko:resources-generator:${rootProject.extra["moko_resources_version"]}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}