package com.softartdev.shared.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Style
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.softartdev.shared.AppState
import com.softartdev.theme.material.PreferableMaterialTheme
import com.softartdev.theme.material.PreferenceItem
import com.softartdev.theme.material.SettingsScaffold
import com.softartdev.theme.material.ThemePreferenceItem
import com.softartdev.theme.material.ThemePreferencesCategory
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.material_design_version
import io.github.softartdev.theme_prefs.generated.resources.switch_to_material_design_3
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
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
        MaterialSwitchPreferenceItem()
    }
}

@Composable
fun MaterialSwitchPreferenceItem() = PreferenceItem(
    title = stringResource(Res.string.material_design_version),
    vector = Icons.Filled.Style,
    secondaryText = { Text(stringResource(Res.string.switch_to_material_design_3)) },
    onClick = AppState.switchMaterialCallback,
    trailing = {
        Switch(
            checked = AppState.showMaterial3.value,
            onCheckedChange = AppState.changeMaterialCallback
        )
    }
)

@Preview
@Composable
fun SettingsBodyPreview() {
    PreferableMaterialTheme { SettingsBody() }
}