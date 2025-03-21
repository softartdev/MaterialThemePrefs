package com.softartdev.shared

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs

@Composable
expect fun EnableEdgeToEdge(material3: Boolean, inDark: Boolean)

val ThemePrefs.inDark: Boolean
    @Composable
    @ReadOnlyComposable
    get() = when (darkThemeState.value) {
        ThemeEnum.Light -> false
        ThemeEnum.Dark -> true
        ThemeEnum.SystemDefault -> isSystemInDarkTheme()
    }
