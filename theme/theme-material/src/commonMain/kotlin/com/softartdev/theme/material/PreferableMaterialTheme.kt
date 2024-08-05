package com.softartdev.theme.material

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.softartdev.theme.pref.*

@Composable
fun PreferableMaterialTheme(
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
