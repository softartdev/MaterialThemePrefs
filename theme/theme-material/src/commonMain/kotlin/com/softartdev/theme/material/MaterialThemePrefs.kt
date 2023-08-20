package com.softartdev.theme.material

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.*

class MaterialThemePrefs(
    preferenceHelper: PreferenceHelper,
    dialogHolder: DialogHolder = DialogHolder(),
    val darkColorPalette: Colors = darkColors(),
    val lightColorPalette: Colors = lightColors()
) : ThemePrefs(preferenceHelper, dialogHolder) {

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = when (darkThemeState.value) {
            ThemeEnum.Light -> lightColorPalette
            ThemeEnum.Dark -> darkColorPalette
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorPalette else lightColorPalette
        }
}

val ThemePrefs.colors: Colors
    @Composable
    @ReadOnlyComposable
    get() {
        val materialThemePrefs = this as MaterialThemePrefs
        return materialThemePrefs.colors
    }

fun ThemePrefs.showDialog() = dialogHolder.showThemeChange(
    darkThemeState = darkThemeState,
    writePref = preferenceHelper::themeEnum::set
)

@Composable
fun rememberThemePrefs(): MaterialThemePrefs {
    val preferenceHelper = rememberPreferenceHelper()
    return remember { MaterialThemePrefs(preferenceHelper) }
}

@Composable
fun rememberThemePrefs(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    dialogHolder: DialogHolder = DialogHolder(),
    darkColorPalette: Colors = darkColors(),
    lightColorPalette: Colors = lightColors()
) = remember(
    key1 = dialogHolder,
    key2 = darkColorPalette,
    key3 = lightColorPalette
) {
    MaterialThemePrefs(
        preferenceHelper = preferHelper,
        dialogHolder = dialogHolder,
        darkColorPalette = darkColorPalette,
        lightColorPalette = lightColorPalette
    )
}
