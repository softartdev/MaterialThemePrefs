package com.softartdev.theme.material

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.PreferableMaterialTheme
import com.softartdev.theme.pref.PreferenceHelper
import com.softartdev.theme.pref.rememberPreferenceHelper

/**
 * Material (v1) theme that respects stored light/dark preference and provides [ThemePrefs] via [LocalThemePrefs].
 *
 * @param preferHelper Backing storage for theme; defaults to [rememberPreferenceHelper].
 * @param darkColorPalette Colors when dark theme is active.
 * @param lightColorPalette Colors when light theme is active.
 * @param content Composable content with access to [PreferableMaterialTheme.themePrefs].
 */
@Composable
public fun PreferableMaterialTheme(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorPalette: Colors = darkColors(),
    lightColorPalette: Colors = lightColors(),
    content: @Composable PreferableMaterialTheme.() -> Unit
) {
    val themePrefs = rememberThemePrefs(preferHelper, darkColorPalette, lightColorPalette)
    CompositionLocalProvider(
        LocalThemePrefs provides themePrefs,
    ) {
        MaterialTheme(
            colors = themePrefs.colors,
            content = { PreferableMaterialTheme.content() }
        )
    }
}
