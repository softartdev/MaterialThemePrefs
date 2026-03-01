package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * [CompositionLocal] providing the current [ThemePrefs] in the composition tree.
 * Must be provided by a theme composable (e.g. [PreferableMaterialTheme]).
 */
public val LocalThemePrefs: ProvidableCompositionLocal<ThemePrefs> = staticCompositionLocalOf {
    error("CompositionLocal LocalThemePrefs not present")
}

/**
 * Base class for theme preferences (light/dark/system).
 * Holds the current theme state and a [PreferenceHelper] for persistence.
 *
 * @param preferenceHelper Backing storage for the selected theme; platform-specific.
 */
public abstract class ThemePrefs(
    public val preferenceHelper: PreferenceHelper,
) {
    /**
     * Current theme selection. Reading is reactive; updates persist via [preferenceHelper].
     */
    public val themeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)
}

/**
 * Object providing access to the current [ThemePrefs] from composition.
 * Use inside a [PreferableMaterialTheme] (or Material3 equivalent) scope.
 */
public object PreferableMaterialTheme {

    /**
     * The current [ThemePrefs] from [LocalThemePrefs].
     */
    public val themePrefs: ThemePrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalThemePrefs.current
}
