@file:OptIn(ExperimentalMaterialApi::class)
@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.softartdev.theme.material

import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.MR
import com.softartdev.theme.pref.ThemePrefs
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ThemePreferencesCategory() = PreferenceCategory(
    title = stringResource(MR.strings.theme),
    vector = Icons.Filled.Brightness4
)

@Composable
fun ThemePreferenceItem(themePrefs: ThemePrefs = LocalThemePrefs.current) = PreferenceItem(
    title = stringResource(MR.strings.choose_theme),
    vector = Icons.Filled.SettingsBrightness,
    secondaryText = { Text(text = themePrefs.darkThemeState.value.toLocalizedString()) },
    onClick = themePrefs::showDialog
)

@Composable
fun PreferenceCategory(title: String, vector: ImageVector) = ListItem(
    icon = { Icon(imageVector = vector, contentDescription = title) },
    text = {
        Text(text = title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondaryVariant)
    }
)

@Composable
fun PreferenceItem(
    title: String,
    vector: ImageVector,
    onClick: () -> Unit = {},
    secondaryText: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) = ListItem(
    modifier = Modifier.clickable(onClick = onClick),
    icon = { Icon(imageVector = vector, contentDescription = title) },
    text = { Text(text = title) },
    secondaryText = secondaryText,
    trailing = trailing
)
