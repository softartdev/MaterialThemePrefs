rootProject.name = "MaterialThemePrefs"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
includeBuild("convention-plugins")

include(":theme:theme-prefs")
include(":theme:theme-material")
include(":theme:theme-material3")

include(":sample:shared")
include(":sample:android")
include(":sample:ios")
include(":sample:desktop")
include(":sample:web")
