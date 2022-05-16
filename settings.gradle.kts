pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
rootProject.name = "MaterialThemePrefs"
includeBuild("convention-plugins")
include(":material-theme-prefs")
include(":sample-shared")
include(":sample-android")
include(":sample-desktop")
include(":sample-ios")
