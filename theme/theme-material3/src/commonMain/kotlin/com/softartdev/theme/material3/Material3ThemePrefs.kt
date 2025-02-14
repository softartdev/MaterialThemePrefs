package com.softartdev.theme.material3

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.*

public class Material3ThemePrefs(
    preferenceHelper: PreferenceHelper,
    private val darkColorScheme: ColorScheme = darkColorScheme(),
    private val lightColorScheme: ColorScheme = lightColorScheme()
) : ThemePrefs(preferenceHelper) {

    public val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = when (darkThemeState.value) {
            ThemeEnum.Light -> lightColorScheme
            ThemeEnum.Dark -> darkColorScheme
            ThemeEnum.SystemDefault -> if (isSystemInDarkTheme()) darkColorScheme else lightColorScheme
        }
}

public val ThemePrefs.colorScheme: ColorScheme
    @Composable
    @ReadOnlyComposable
    get() {
        val material3ThemePrefs = this as Material3ThemePrefs
        return material3ThemePrefs.colorScheme
    }

@Composable
public fun rememberThemePrefs(): Material3ThemePrefs {
    val preferenceHelper = rememberPreferenceHelper()
    return remember { Material3ThemePrefs(preferenceHelper) }
}

@Composable
public fun rememberThemePrefs(
    preferHelper: PreferenceHelper = rememberPreferenceHelper(),
    darkColorScheme: ColorScheme = darkColorScheme(),
    lightColorScheme: ColorScheme = lightColorScheme()
): Material3ThemePrefs = remember(
    key1 = preferHelper,
    key2 = darkColorScheme,
    key3 = lightColorScheme
) {
    Material3ThemePrefs(
        preferenceHelper = preferHelper,
        darkColorScheme = darkColorScheme,
        lightColorScheme = lightColorScheme
    )
}
