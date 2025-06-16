# Material Theme Preferences

![Maven Central](https://img.shields.io/maven-central/v/io.github.softartdev/theme-material)
[![Build & Publish CI/CD](https://github.com/softartdev/MaterialThemePrefs/actions/workflows/build_publish.yml/badge.svg)](https://github.com/softartdev/MaterialThemePrefs/actions/workflows/build_publish.yml)

Kotlin Multiplatform library for easy switching Dark/Light Material themes on [Compose](https://github.com/JetBrains/compose-jb).
Supported platforms:
- Android
- iOS
- Desktop JVM (MacOS, Linux, Windows)
- Web (wasm JS)

![Android screenshot](doc/screenshots/gif/android/material_design_3/demo.gif)
![iOS screenshot](doc/screenshots/gif/iOS/material_design_3/demo.gif)
![Desktop screenshot](doc/screenshots/gif/desktop/material_design_3/demo.gif)
<details>
    <summary>More…</summary>
    <p><img src="doc/screenshots/gif/android/material_design_2/demo.gif"> <img src="doc/screenshots/gif/iOS/material_design_2/demo.gif"> <img src="doc/screenshots/gif/desktop/material_design_2/demo.gif"></p>
</details>

## Usage
Call composable functions to wrap your app, show theme preference items, and handle dialog state manually.

**Show dialog directly:**
Use `ThemeAlertDialog` to show a dialog with theme preferences:
```kotlin
@Composable
fun App() = PreferableMaterialTheme {
    var showDialog by remember { mutableStateOf(false) }
    SettingsScaffold(onThemeClick = { showDialog = true }) {
        Column {
            ThemePreferencesCategory() // subtitle
            ThemePreferenceItem(onClick = { showDialog = true }) // menu item
        }
    }
    if (showDialog) {
        ThemeAlertDialog(dismissDialog = { showDialog = false })
    }
}
```
**With navigation frameworks:**
Use `ThemeDialogContent` as the content of a dialog destination:
```kotlin
@Composable
fun App() = PreferableMaterialTheme {
    val navController = rememberNavController()
    NavHost(navController = navController) {
        composable<AppNavGraph.Settings> {
            SettingsBody(onThemeClick = { navController.navigate(route = AppNavGraph.ThemeDialog) })
        }
        dialog<AppNavGraph.ThemeDialog> {
            ThemeDialogContent(dismissDialog = navController::popBackStack)
        }
    }
}
```
Check the [sample app](sample/src/commonMain/kotlin/com/softartdev/shared/App.kt) for using with Material Design versions 2 & 3.

The [NoteDelight](https://github.com/softartdev/NoteDelight/blob/master/shared-compose-ui/src/commonMain/kotlin/com/softartdev/notedelight/ui/SettingsScreen.kt#L104) app is a real example.   
## Installation
The latest release is available on [Maven Central](https://repo1.maven.org/maven2/io/github/softartdev/theme-material/).
### Gradle
1. Add the Maven Central repository if it is not already there:
```kotlin
repositories {
    mavenCentral()
}
```
2. In multiplatform projects, add a dependency to the commonMain source set dependencies
```kotlin
commonMain {
    dependencies {
        implementation("io.github.softartdev:theme-material:$latestVersion") // Material Design 2
        implementation("io.github.softartdev:theme-material3:$latestVersion") // Material Design 3
        implementation("io.github.softartdev:theme-prefs:$latestVersion") // optional, if you need only preferences
    }
}
```
## Implementation
Used [compose-multiplatform-resources](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-resources.html) library for many languages (currently Russian and English are supported).

Persisting preferences is implemented using [SharedPreferences](https://developer.android.com/reference/android/content/SharedPreferences) on Android, and [Java Preference API](https://docs.oracle.com/javase/7/docs/api/java/util/prefs/Preferences.html) on JVM Desktop.
```kotlin
// common:
expect var themeEnum: ThemeEnum

// android:
private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

actual var themeEnum: ThemeEnum
    get() = preferences.getInt(THEME_KEY, ThemeEnum.SystemDefault.ordinal).let(ThemeEnum.values()::get)
    set(value) = preferences.edit().putInt(THEME_KEY, value.ordinal).apply()

// desktop java:
private var preferences: Preferences = Preferences.userNodeForPackage(ThemeEnum::class.java)

actual var themeEnum: ThemeEnum
    get() = preferences.getInt(THEME_KEY, ThemeEnum.SystemDefault.ordinal).let(ThemeEnum.values()::get)
    set(value) = preferences.putInt(THEME_KEY, value.ordinal)

// ios:
private val preferences: NSUserDefaults = NSUserDefaults.standardUserDefaults

actual var themeEnum: ThemeEnum
    get() = preferences.integerForKey(THEME_KEY).let(ThemeEnum.values()::get)
    set(value) = preferences.setInteger(value.ordinal, THEME_KEY)

// wasm js:
private val storage: Storage = window.localStorage

actual var themeEnum: ThemeEnum
    get() = storage.getItem(THEME_KEY)?.toIntOrNull()?.let(ThemeEnum.values()::get) ?: ThemeEnum.SystemDefault
    set(value) = storage.setItem(THEME_KEY, value.ordinal.toString())
```
Also used [composition local](https://developer.android.com/jetpack/compose/compositionlocal) for access from theme-scoped as an implicit way:
```kotlin
val themePrefs: ThemePrefs = LocalThemePrefs.current
```
