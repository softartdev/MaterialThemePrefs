package com.softartdev.theme.material

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.PreferenceHelper
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs
import com.softartdev.theme.pref.rememberPreferenceHelper

/**
 * [ThemePrefs] implementation for Material (v1) theming.
 * Provides [colors] that switch based on [ThemeEnum] (light/dark/system).
 *
 * @param preferenceHelper Backing storage for the selected theme.
 * @param darkColorPalette Colors used when dark theme is active. Defaults to [darkColors].
 * @param lightColorPalette Colors used when light theme is active. Defaults to [lightColors].
 */
public class MaterialThemePrefs(
    preferenceHelper: PreferenceHelper,
    private val darkColorPalette: Colors = darkColors(),
    private val lightColorPalette: Colors = lightColors()
) : ThemePrefs(preferenceHelper) {

    /**
     * Current [Colors] based on [ThemeEnum]. For [ThemeEnum.SystemDefault], follows system dark/light.
     */
    public val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = when (themeState.value) {
            ThemeEnum.Light -> lightColorPalette
            ThemeEnum.Dark -> darkColorPalette
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorPalette else lightColorPalette
        }
}

/**
 * Returns [Colors] when this [ThemePrefs] is a [MaterialThemePrefs]; use inside Material theme scope.
 */
public val ThemePrefs.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() {
        val materialThemePrefs = this as MaterialThemePrefs
        return materialThemePrefs.colors
    }

/**
 * Returns a [MaterialThemePrefs] remembered across recompositions using default [rememberPreferenceHelper].
 */
@Composable
public fun rememberThemePrefs(): MaterialThemePrefs {
    val preferenceHelper = rememberPreferenceHelper()
    return remember { MaterialThemePrefs(preferenceHelper) }
}

/**
 * Returns a [MaterialThemePrefs] with the given [PreferenceHelper] and color palettes.
 *
 * @param preferHelper Backing storage for theme preference.
 * @param darkColorPalette Colors for dark theme.
 * @param lightColorPalette Colors for light theme.
 * @return Remembered [MaterialThemePrefs] instance.
 */
@Composable
public fun rememberThemePrefs(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorPalette: Colors = darkColors(),
    lightColorPalette: Colors = lightColors()
): MaterialThemePrefs = remember(
    key1 = preferHelper,
    key2 = darkColorPalette,
    key3 = lightColorPalette
) {
    MaterialThemePrefs(
        preferenceHelper = preferHelper,
        darkColorPalette = darkColorPalette,
        lightColorPalette = lightColorPalette
    )
}
