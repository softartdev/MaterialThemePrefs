package com.softartdev.theme.material3

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.PreferableMaterialTheme
import com.softartdev.theme.pref.PreferenceHelper
import com.softartdev.theme.pref.rememberPreferenceHelper

/**
 * Material 3 theme that respects stored light/dark preference and provides [ThemePrefs] via [LocalThemePrefs].
 *
 * @param preferHelper Backing storage for theme; defaults to [rememberPreferenceHelper].
 * @param darkColorScheme Color scheme when dark theme is active.
 * @param lightColorScheme Color scheme when light theme is active.
 * @param content Composable content with access to [PreferableMaterialTheme.themePrefs].
 */
@Composable
public fun PreferableMaterialTheme(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorScheme: ColorScheme = darkColorScheme(),
    lightColorScheme: ColorScheme = lightColorScheme(),
    content: @Composable PreferableMaterialTheme.() -> Unit
) {
    val themePrefs = rememberThemePrefs(preferHelper, darkColorScheme, lightColorScheme)
    CompositionLocalProvider(
        LocalThemePrefs provides themePrefs,
    ) {
        MaterialTheme(
            colorScheme = themePrefs.colorScheme,
            content = { PreferableMaterialTheme.content() }
        )
    }
}
