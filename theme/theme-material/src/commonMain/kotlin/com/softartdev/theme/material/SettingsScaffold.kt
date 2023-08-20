@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.softartdev.theme.material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.softartdev.theme.pref.MR
import dev.icerock.moko.resources.compose.stringResource

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
    localizedText: String = stringResource(MR.strings.settings),
    backVector: ImageVector = Icons.Default.ArrowBack,
) = TopAppBar(
    title = { Text(localizedText) },
    navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(imageVector = backVector, contentDescription = backVector.name)
        }
    },
    actions = actions
)