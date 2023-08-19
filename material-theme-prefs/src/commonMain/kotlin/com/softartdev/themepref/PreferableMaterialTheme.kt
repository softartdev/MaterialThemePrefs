package com.softartdev.themepref

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalThemePrefs = staticCompositionLocalOf<ThemePrefs> {
    error("CompositionLocal LocalThemePrefs not present")
}

@Composable
fun PreferableMaterialTheme(
    content: @Composable PreferableMaterialTheme.() -> Unit
) {
    val themePrefs = rememberThemePrefs()
    CompositionLocalProvider(
        LocalThemePrefs provides themePrefs,
    ) {
        MaterialTheme(
            colorScheme = themePrefs.colorScheme,
            content = { PreferableMaterialTheme.content() }
        )
    }
}

object PreferableMaterialTheme {

    val themePrefs: ThemePrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalThemePrefs.current
}