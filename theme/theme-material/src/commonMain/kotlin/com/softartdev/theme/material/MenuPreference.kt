@file:OptIn(ExperimentalMaterialApi::class)

package com.softartdev.theme.material

import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.ThemePrefs
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.choose_theme
import io.github.softartdev.theme_prefs.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun ThemePreferencesCategory(): Unit = PreferenceCategory(
    title = stringResource(Res.string.theme),
    vector = Icons.Filled.Brightness4
)

@Composable
public fun ThemePreferenceItem(
    themePrefs: ThemePrefs = LocalThemePrefs.current,
    onClick: () -> Unit = {}
): Unit = PreferenceItem(
    title = stringResource(Res.string.choose_theme),
    vector = Icons.Filled.SettingsBrightness,
    secondaryText = { Text(text = stringResource(themePrefs.darkThemeState.value.stringRes)) },
    onClick = onClick
)

@Composable
public fun PreferenceCategory(title: String, vector: ImageVector): Unit = ListItem(
    icon = { Icon(imageVector = vector, contentDescription = title) },
    text = {
        Text(text = title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondaryVariant)
    }
)

@Composable
public fun PreferenceItem(
    title: String,
    vector: ImageVector,
    onClick: () -> Unit = {},
    secondaryText: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
): Unit = ListItem(
    modifier = Modifier.clickable(onClick = onClick),
    icon = { Icon(imageVector = vector, contentDescription = title) },
    text = { Text(text = title) },
    secondaryText = secondaryText,
    trailing = trailing
)

@Preview
@Composable
public fun ThemePreferencesCategoryPreview() {
    ThemePreferencesCategory()
}

@Preview
@Composable
public fun ThemePreferenceItemPreview() {
    PreferableMaterialTheme { ThemePreferenceItem() }
}