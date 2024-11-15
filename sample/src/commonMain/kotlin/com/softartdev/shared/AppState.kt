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
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.switch_to_material_design_2
import io.github.softartdev.theme_prefs.generated.resources.switch_to_material_design_3
import org.jetbrains.compose.resources.stringResource

object AppState {
    val showMaterial3: MutableState<Boolean> = mutableStateOf(false)
    val textState: MutableState<String> = mutableStateOf(readMe)
    val scrollState: ScrollState = ScrollState(initial = 0)

    val secondaryText: String
        @Composable get() = stringResource(
            resource = when (showMaterial3.value) {
                true -> Res.string.switch_to_material_design_2
                false -> Res.string.switch_to_material_design_3
            }
        )

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