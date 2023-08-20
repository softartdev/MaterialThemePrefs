package com.softartdev.theme.pref

import androidx.compose.runtime.*

val LocalThemePrefs = staticCompositionLocalOf<ThemePrefs> {
    error("CompositionLocal LocalThemePrefs not present")
}

abstract class ThemePrefs(
    val preferenceHelper: PreferenceHelper,
    val dialogHolder: DialogHolder = DialogHolder(),
) {
    val darkThemeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)

    @Composable
    fun showDialogIfNeed() = dialogHolder.showDialogIfNeed()
}

object PreferableMaterialTheme {

    val themePrefs: ThemePrefs
        @Composable
        @ReadOnlyComposable
        get() = LocalThemePrefs.current
}
