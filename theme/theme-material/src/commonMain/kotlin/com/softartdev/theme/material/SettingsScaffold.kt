package com.softartdev.theme.material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

/**
 * Scaffold with a top app bar suitable for settings screens (back navigation + optional actions).
 *
 * @param onBackClick Called when the back button is clicked.
 * @param actions Optional trailing actions in the top bar.
 * @param content Main content with [PaddingValues] for the scaffold insets.
 */
@Composable
public fun SettingsScaffold(
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
): Unit = Scaffold(
    topBar = { SettingsTopAppBar(onBackClick, actions) },
    content = content
)

/**
 * Top app bar with back button and title for settings screens.
 *
 * @param onBackClick Called when the back icon is clicked.
 * @param actions Optional trailing actions.
 * @param localizedText Title text; defaults to "Settings" string resource.
 * @param backVector Icon for the back button; defaults to ArrowBack.
 */
@Composable
public fun SettingsTopAppBar(
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    localizedText: String = stringResource(Res.string.settings),
    backVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
): Unit = TopAppBar(
    title = { Text(localizedText) },
    navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = backVector, contentDescription = backVector.name)
        }
    },
    actions = actions
)

@Preview
@Composable
public fun SettingsScaffoldPreview() {
    PreferableMaterialTheme {
        SettingsScaffold { paddingValues ->
            Text(
                modifier = Modifier.padding(paddingValues),
                text = stringResource(Res.string.settings),
            )
        }
    }
}