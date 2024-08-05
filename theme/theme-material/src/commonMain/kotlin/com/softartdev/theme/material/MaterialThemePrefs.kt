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

class MaterialThemePrefs(
    preferenceHelper: PreferenceHelper,
    val darkColorPalette: Colors = darkColors(),
    val lightColorPalette: Colors = lightColors()
) : ThemePrefs(preferenceHelper) {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = when (darkThemeState.value) {
            ThemeEnum.Light -> lightColorPalette
            ThemeEnum.Dark -> darkColorPalette
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorPalette else lightColorPalette
        }
}

val ThemePrefs.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() {
        val materialThemePrefs = this as MaterialThemePrefs
        return materialThemePrefs.colors
    }

@Composable
fun rememberThemePrefs(): MaterialThemePrefs {
    val preferenceHelper = rememberPreferenceHelper()
    return remember { MaterialThemePrefs(preferenceHelper) }
}

@Composable
fun rememberThemePrefs(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorPalette: Colors = darkColors(),
    lightColorPalette: Colors = lightColors()
) = remember(
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
