buildscript {
    extra["moko_resources_version"] = "0.20.1"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.2.1")
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