package com.softartdev.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.softartdev.themepref.PreferableMaterialTheme
import com.softartdev.themepref.SettingsScaffold
import com.softartdev.themepref.ThemePreferenceItem
import com.softartdev.themepref.ThemePreferencesCategory

@Composable
fun App() {
    PreferableMaterialTheme {
        SettingsScaffold {
            Box {
                Column {
                    ThemePreferencesCategory()
                    ThemePreferenceItem()
                }
                themePrefs.showDialogIfNeed()
            }
        }
    }
}