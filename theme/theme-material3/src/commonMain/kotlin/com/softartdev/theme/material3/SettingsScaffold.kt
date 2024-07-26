@file:OptIn(ExperimentalMaterial3Api::class)

package com.softartdev.theme.material3

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.settings
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScaffold(
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = Scaffold(
    topBar = { SettingsTopAppBar(onBackClick, actions) },
    content = content
)

@Composable
fun SettingsTopAppBar(
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    localizedText: String = stringResource(Res.string.settings),
    backVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
) = TopAppBar(
    title = { Text(localizedText) },
    navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = backVector, contentDescription = backVector.name)
        }
    },
    actions = actions
)
