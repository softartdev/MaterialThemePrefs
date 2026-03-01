package com.softartdev.theme.material3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.PreferenceHelper
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs
import com.softartdev.theme.pref.rememberPreferenceHelper

/**
 * [ThemePrefs] implementation for Material 3 theming.
 * Provides [colorScheme] that switches based on [ThemeEnum] (light/dark/system).
 *
 * @param preferenceHelper Backing storage for the selected theme.
 * @param darkColorScheme Color scheme used when dark theme is active. Defaults to [darkColorScheme].
 * @param lightColorScheme Color scheme used when light theme is active. Defaults to [lightColorScheme].
 */
public class Material3ThemePrefs(
    preferenceHelper: PreferenceHelper,
    private val darkColorScheme: ColorScheme = darkColorScheme(),
    private val lightColorScheme: ColorScheme = lightColorScheme()
) : ThemePrefs(preferenceHelper) {

    /**
     * Current [ColorScheme] based on [ThemeEnum]. For [ThemeEnum.SystemDefault], follows system dark/light.
     */
    public val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = when (themeState.value) {
            ThemeEnum.Light -> lightColorScheme
            ThemeEnum.Dark -> darkColorScheme
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        }
}

/**
 * Returns [ColorScheme] when this [ThemePrefs] is a [Material3ThemePrefs]; use inside Material 3 theme scope.
 */
public val ThemePrefs.colorScheme: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() {
        val material3ThemePrefs = this as Material3ThemePrefs
        return material3ThemePrefs.colorScheme
    }

/**
 * Returns a [Material3ThemePrefs] remembered across recompositions using default [rememberPreferenceHelper].
 */
@Composable
public fun rememberThemePrefs(): Material3ThemePrefs {
    val preferenceHelper = rememberPreferenceHelper()
    return remember { Material3ThemePrefs(preferenceHelper) }
}

/**
 * Returns a [Material3ThemePrefs] with the given [PreferenceHelper] and color schemes.
 *
 * @param preferHelper Backing storage for theme preference.
 * @param darkColorScheme Color scheme for dark theme.
 * @param lightColorScheme Color scheme for light theme.
 * @return Remembered [Material3ThemePrefs] instance.
 */
@Composable
public fun rememberThemePrefs(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorScheme: ColorScheme = darkColorScheme(),
    lightColorScheme: ColorScheme = lightColorScheme()
): Material3ThemePrefs = remember(
    key1 = preferHelper,
    key2 = darkColorScheme,
    key3 = lightColorScheme
) {
    Material3ThemePrefs(
        preferenceHelper = preferHelper,
        darkColorScheme = darkColorScheme,
        lightColorScheme = lightColorScheme
    )
}
