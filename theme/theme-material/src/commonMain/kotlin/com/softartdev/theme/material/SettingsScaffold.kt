package com.softartdev.theme.material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

@Composable
public fun SettingsScaffold(
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
): Unit = Scaffold(
    topBar = { SettingsTopAppBar(onBackClick, actions) },
    content = content
)

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