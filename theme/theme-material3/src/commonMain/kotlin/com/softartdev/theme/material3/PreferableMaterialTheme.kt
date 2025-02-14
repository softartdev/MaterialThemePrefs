package com.softartdev.theme.material3

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.softartdev.theme.pref.*

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
