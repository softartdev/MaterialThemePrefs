package com.softartdev.theme.pref

import androidx.compose.runtime.*

val LocalThemePrefs = staticCompositionLocalOf<ThemePrefs> {
    error("CompositionLocal LocalThemePrefs not present")
}

abstract class ThemePrefs(
    val preferenceHelper: PreferenceHelper,
) {
    val darkThemeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)
}

object PreferableMaterialTheme {

    val themePrefs: ThemePrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalThemePrefs.current
}
