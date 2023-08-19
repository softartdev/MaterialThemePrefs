package com.softartdev.themepref

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*

class ThemePrefs(
    val preferenceHelper: PreferenceHelper,
    val dialogHolder: DialogHolder = DialogHolder(),
    val darkColorScheme: ColorScheme = darkColorScheme(),
    val lightColorScheme: ColorScheme = lightColorScheme()
) {
    val darkThemeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)

    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = when (darkThemeState.value) {
            ThemeEnum.Light -> lightColorScheme
            ThemeEnum.Dark -> darkColorScheme
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        }

    fun showDialog() = dialogHolder.showThemeChange(
        darkThemeState = darkThemeState,
        writePref = preferenceHelper::themeEnum::set
    )

    @Composable
    fun showDialogIfNeed() = dialogHolder.showDialogIfNeed()
}

@Composable
fun rememberThemePrefs(): ThemePrefs {
    val preferenceHelper = obtainPreferenceHelper()
    return remember { ThemePrefs(preferenceHelper) }
}

@Composable
fun rememberThemePrefs(
    preferHelper: PreferenceHelper = obtainPreferenceHelper(),
    obtainDialogHolder: () -> DialogHolder = ::DialogHolder,
    obtainDarkColorScheme: () -> ColorScheme = { darkColorScheme() },
    obtainLightColorScheme: () -> ColorScheme = { lightColorScheme() }
) = remember(
    key1 = obtainDialogHolder,
    key2 = obtainDarkColorScheme,
    key3 = obtainLightColorScheme
) {
    ThemePrefs(
        preferenceHelper = preferHelper,
        dialogHolder = obtainDialogHolder(),
        darkColorScheme = obtainDarkColorScheme(),
        lightColorScheme = obtainLightColorScheme()
    )
}
