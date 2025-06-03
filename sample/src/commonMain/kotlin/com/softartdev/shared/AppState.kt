package com.softartdev.shared

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.mutableStateOf
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.PreferableMaterialTheme.themePrefs
import com.softartdev.theme.pref.ThemeEnum
import com.softartdev.theme.pref.ThemePrefs

object AppState {
    val showMaterial3: MutableState<Boolean> = mutableStateOf(false)
    val textState: MutableState<String> = mutableStateOf(readMe)
    val scrollState: ScrollState = ScrollState(initial = 0)

    val scrollProgress: Float
        get() = scrollState.value.toFloat() / scrollState.maxValue.toFloat()

    val isDarkTheme: Boolean
        @Composable
        @ReadOnlyComposable
        get() = when (themePrefs.darkThemeState.value) {
            ThemeEnum.Light -> false
            ThemeEnum.Dark -> true
            ThemeEnum.SystemDefault -> isSystemInDarkTheme()
        }

    val switchThemeCallback: () -> Unit
        @Composable
        @ReadOnlyComposable
        get() {
            val currentIsDark: Boolean = isDarkTheme
            val themePrefs: ThemePrefs = LocalThemePrefs.current
            val darkThemeState = themePrefs.darkThemeState
            val prefHelper = themePrefs.preferenceHelper
            return {
                darkThemeState.value = if (currentIsDark) ThemeEnum.Light else ThemeEnum.Dark
                prefHelper.themeEnum = darkThemeState.value
            }
        }

    val changeMaterialCallback: (Boolean) -> Unit = showMaterial3::value::set
    val switchMaterialCallback: () -> Unit = { showMaterial3.value = !showMaterial3.value }
}