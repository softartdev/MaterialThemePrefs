buildscript {
    extra["kotlin_version"] = "2.1.21"
    extra["android_min_sdk_version"] = 21
    extra["android_compile_sdk_version"] = 36
    extra["jdk_version"] = 17
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin_version"]}")
        classpath("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${rootProject.extra["kotlin_version"]}")
        classpath("com.android.tools.build:gradle:8.10.1")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.8.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${rootProject.extra["kotlin_version"]}")
    }
}
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}