package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

/**
 * Platform-specific storage for theme preference.
 * Implementations persist the selected [ThemeEnum] (e.g. SharedPreferences on Android, Preferences on JVM).
 */
public interface PreferenceHelper {

    /**
     * The stored theme. Reading returns the last saved value; writing persists it.
     */
    public var themeEnum: ThemeEnum

    /**
     * Clears all stored theme preference data.
     */
    public fun clear()
}

internal const val THEME_KEY: String = "theme_key"

@Composable
internal expect fun obtainPreferenceHelper(): PreferenceHelper

/**
 * Returns a [PreferenceHelper] remembered across recompositions.
 * Use this to obtain the platform-specific preference backend for [ThemePrefs].
 *
 * @return A [PreferenceHelper] for the current platform (Android, JVM, iOS, Wasm).
 */
@Composable
public fun rememberPreferenceHelper(): PreferenceHelper {
    val preferenceHelper = obtainPreferenceHelper()
    return remember { preferenceHelper }
}