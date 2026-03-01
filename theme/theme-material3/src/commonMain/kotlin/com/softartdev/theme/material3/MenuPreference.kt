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
 * Non-clickable list item used as a section header (leading icon + headline in tertiary style).
 *
 * @param title Section title.
 * @param vector Icon for the section.
 */
@Composable
public fun PreferenceCategory(title: String, vector: ImageVector): Unit = ListItem(
    leadingContent = { Icon(imageVector = vector, contentDescription = title) },
    headlineContent = {
        Text(text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.tertiary)
    }
)

/**
 * Clickable list row for a preference: leading icon, headline, optional supporting/trailing content.
 *
 * @param title Primary text (headline).
 * @param vector Icon for the row.
 * @param onClick Called when the row is clicked.
 * @param secondaryText Optional supporting text or composable.
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
    leadingContent = { Icon(imageVector = vector, contentDescription = title) },
    headlineContent = { Text(text = title) },
    supportingContent = secondaryText,
    trailingContent = trailing
)

@Preview
@Composable
public fun ThemePreferencesCategoryPreview() {
    PreferableMaterialTheme { ThemePreferencesCategory() }
}

@Preview
@Composable
public fun ThemePreferenceItemPreview() {
    PreferableMaterialTheme { ThemePreferenceItem() }
}