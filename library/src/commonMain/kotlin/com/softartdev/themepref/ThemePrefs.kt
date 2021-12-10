package com.softartdev.themepref

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*

class ThemePrefs(
    val preferenceHelper: PreferenceHelper,
    val dialogHolder: DialogHolder = DialogHolder(),
    private val DarkColorPalette: Colors = darkColors(),
    private val LightColorPalette: Colors = lightColors()
) {
    val darkThemeState: MutableState<ThemeEnum> = mutableStateOf(preferenceHelper.themeEnum)

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = when (darkThemeState.value) {
            ThemeEnum.Light -> LightColorPalette
            ThemeEnum.Dark -> DarkColorPalette
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
        }

    fun showDialog() = dialogHolder.showThemeChange(
        darkThemeState = darkThemeState,
        writePref = preferenceHelper::themeEnum::set
    )
}

@Composable
fun rememberThemePrefs(): ThemePrefs {
    val preferenceHelper = obtainPreferenceHelper()
    return remember { ThemePrefs(preferenceHelper) }
}