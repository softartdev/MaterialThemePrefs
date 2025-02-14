package com.softartdev.theme.material3

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.softartdev.theme.pref.LocalThemePrefs
import io.github.softartdev.theme_prefs.generated.resources.Res
import com.softartdev.theme.pref.ThemePrefs
import io.github.softartdev.theme_prefs.generated.resources.choose_theme
import io.github.softartdev.theme_prefs.generated.resources.theme
import org.jetbrains.compose.resources.stringResource

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
    leadingContent = { Icon(imageVector = vector, contentDescription = title) },
    headlineContent = {
        Text(text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.tertiary)
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
    leadingContent = { Icon(imageVector = vector, contentDescription = title) },
    headlineContent = { Text(text = title) },
    supportingContent = secondaryText,
    trailingContent = trailing
)
