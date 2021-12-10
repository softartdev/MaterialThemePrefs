package com.softartdev.shared

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.softartdev.themepref.*

@Composable
fun App() {
    PreferableMaterialTheme {
        val themePrefs = LocalThemePrefs.current
        SettingsScreenBody(
            changeTheme = themePrefs::showDialog,
            showDialogIfNeed = { themePrefs.dialogHolder.showDialogIfNeed() },
            currentThemeTextContent = { Text(text = themePrefs.darkThemeState.value.toLocalizedString()) },
        )
    }
}
