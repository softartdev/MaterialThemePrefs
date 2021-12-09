pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "MaterialThemePrefs"

include(":library")
include(":sample-shared")
include(":sample-android")
include(":sample-desktop")