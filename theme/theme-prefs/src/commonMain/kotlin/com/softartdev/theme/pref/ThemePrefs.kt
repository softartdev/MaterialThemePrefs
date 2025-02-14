package com.softartdev.theme.pref

import androidx.compose.runtime.*

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
