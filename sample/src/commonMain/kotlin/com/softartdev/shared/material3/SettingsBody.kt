package com.softartdev.shared.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.softartdev.shared.AppState
import com.softartdev.theme.material3.PreferenceItem
import com.softartdev.theme.material3.SettingsScaffold
import com.softartdev.theme.material3.ThemePreferenceItem
import com.softartdev.theme.material3.ThemePreferencesCategory
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.material_version
import org.jetbrains.compose.resources.stringResource
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun SettingsBody(
    onBackClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
) = SettingsScaffold(onBackClick, barActions()) { paddingValues -> // includes TopAppBar
    Column(modifier = Modifier.padding(paddingValues)) {
        ThemePreferencesCategory() // subtitle
        ThemePreferenceItem(onClick = onThemeClick) // menu item
        Material3SwitchPreferenceItem()
    }
}

@Composable
fun Material3SwitchPreferenceItem() = PreferenceItem(
    title = stringResource(Res.string.material_version),
    vector = Icons.Filled.Style,
    secondaryText = { Text(AppState.secondaryText) },
    onClick = AppState.switchMaterialCallback,
    trailing = {
        Switch(
            checked = AppState.showMaterial3.value,
            onCheckedChange = AppState.changeMaterialCallback
        )
    }
)
