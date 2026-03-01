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
import androidx.compose.ui.tooling.preview.Preview
import com.softartdev.theme.pref.LocalThemePrefs
import com.softartdev.theme.pref.ThemePrefs
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.choose_theme
import io.github.softartdev.theme_prefs.generated.resources.theme
import org.jetbrains.compose.resources.stringResource

/**
 * List item used as a category header for the "Theme" section in preference screens.
 */
@Composable
public fun ThemePreferencesCategory(): Unit = PreferenceCategory(
    title = stringResource(Res.string.theme),
    vector = Icons.Filled.Brightness4
)

/**
 * List item that opens the theme picker when clicked. Shows current theme in secondary text.
 *
 * @param themePrefs [ThemePrefs] to read current theme from; defaults to [LocalThemePrefs.current].
 * @param onClick Called when the row is clicked (e.g. to show theme dialog).
 */
@Composable
public fun ThemePreferenceItem(
    themePrefs: ThemePrefs = LocalThemePrefs.current,
    onClick: () -> Unit = {}
): Unit = PreferenceItem(
    title = stringResource(Res.string.choose_theme),
    vector = Icons.Filled.SettingsBrightness,
    secondaryText = { Text(text = stringResource(themePrefs.themeState.value.stringRes)) },
    onClick = onClick
)

/**
 * Non-clickable list item used as a section header (icon + title in secondary style).
 *
 * @param title Section title.
 * @param vector Icon for the section.
 */
@Composable
public fun PreferenceCategory(title: String, vector: ImageVector): Unit = ListItem(
    icon = { Icon(imageVector = vector, contentDescription = title) },
    text = {
        Text(text = title,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.secondaryVariant)
    }
)

/**
 * Clickable list row for a preference: icon, title, optional secondary/trailing content.
 *
 * @param title Primary text.
 * @param vector Icon for the row.
 * @param onClick Called when the row is clicked.
 * @param secondaryText Optional secondary text or composable.
 * @param trailing Optional trailing composable.
 */
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

@Preview(showBackground = true)
@Composable
public fun ThemePreferencesCategoryPreview() {
    ThemePreferencesCategory()
}

@Preview(showBackground = true)
@Composable
public fun ThemePreferenceItemPreview() {
    PreferableMaterialTheme { ThemePreferenceItem() }
}