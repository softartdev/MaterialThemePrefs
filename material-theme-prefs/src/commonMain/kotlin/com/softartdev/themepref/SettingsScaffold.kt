@file:OptIn(ExperimentalMaterialApi::class)
@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.softartdev.themepref

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SettingsScaffold(
    onBackClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) = Scaffold(
    topBar = { SettingsTopAppBar(onBackClick) },
    content = content
)

@Composable
fun SettingsTopAppBar(
    onBackClick: () -> Unit = {},
    localizedText: String = MR.strings.settings.composeLocalized(),
    backVector: ImageVector = Icons.Default.ArrowBack
) = TopAppBar(
    title = { Text(localizedText) },
    navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = backVector, contentDescription = backVector.name)
        }
    },
)