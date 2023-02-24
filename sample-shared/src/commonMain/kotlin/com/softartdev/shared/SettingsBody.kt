package com.softartdev.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.softartdev.themepref.PreferableMaterialTheme
import com.softartdev.themepref.SettingsScaffold
import com.softartdev.themepref.ThemePreferenceItem
import com.softartdev.themepref.ThemePreferencesCategory
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun SettingsBody(
    onBackClick: () -> Unit = {}
) = SettingsScaffold(onBackClick) { // includes TopAppBar
    Box {
        Column {
            ThemePreferencesCategory() // subtitle
            ThemePreferenceItem() // menu item
        }
        PreferableMaterialTheme.themePrefs.showDialogIfNeed() // shows when menu item clicked
    }
}