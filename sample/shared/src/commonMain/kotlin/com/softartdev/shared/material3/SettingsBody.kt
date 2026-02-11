package com.softartdev.shared.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import com.softartdev.shared.AppState
import com.softartdev.theme.material3.PreferableMaterialTheme
import com.softartdev.theme.material3.PreferenceCategory
import com.softartdev.theme.material3.PreferenceItem
import com.softartdev.theme.material3.SettingsScaffold
import com.softartdev.theme.material3.ThemePreferenceItem
import com.softartdev.theme.material3.ThemePreferencesCategory
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.info
import io.github.softartdev.theme_prefs.generated.resources.material_design_version
import io.github.softartdev.theme_prefs.generated.resources.pref_subtitle_open_github
import io.github.softartdev.theme_prefs.generated.resources.pref_title_source_code
import io.github.softartdev.theme_prefs.generated.resources.switch_to_material_design_3
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
    val uriHandler = LocalUriHandler.current
    Column(modifier = Modifier.padding(paddingValues)) {
        ThemePreferencesCategory() // subtitle
        ThemePreferenceItem(onClick = onThemeClick) // menu item
        Material3SwitchPreferenceItem()
        PreferenceCategory(stringResource(Res.string.info), Icons.Default.Info)
        PreferenceItem(
            title = stringResource(Res.string.pref_title_source_code),
            vector = Icons.Default.Code,
            onClick = { uriHandler.openUri("https://github.com/softartdev/MaterialThemePrefs") },
            secondaryText = { Text(stringResource(Res.string.pref_subtitle_open_github)) }
        )
    }
}

@Composable
fun Material3SwitchPreferenceItem() = PreferenceItem(
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