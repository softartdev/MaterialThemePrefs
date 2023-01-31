pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "MaterialThemePrefs"
includeBuild("convention-plugins")
include(":material-theme-prefs")
include(":sample-shared")
include(":sample-android")
include(":sample-desktop")
include(":sample-ios")
