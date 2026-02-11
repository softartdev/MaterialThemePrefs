package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf

public val LocalThemePrefs: ProvidableCompositionLocal<ThemePrefs> = staticCompositionLocalOf {
    error("CompositionLocal LocalThemePrefs not present")
}

public abstract class ThemePrefs(
    public val preferenceHelper: PreferenceHelper,
) {
    public val darkThemeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)
}

public object PreferableMaterialTheme {

    public val themePrefs: ThemePrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalThemePrefs.current
}
