package com.softartdev.shared.material

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material.icons.filled.Style
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softartdev.shared.AppState
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun NoteDetailBody(
    textState: MutableState<String> = remember(AppState::textState),
    onBackClick: () -> Unit = {},
    showLoading: Boolean = true,
    scrollState: ScrollState = remember(AppState::scrollState),
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "Title", maxLines = 1) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = barActions()
        )
    }) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (showLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        TextField(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(state = scrollState),
            label = { Text("Type text") },
            value = textState.value,
            onValueChange = { textState.value = it },
        )
    }
}

fun barActions(): @Composable (RowScope.() -> Unit) = {
    IconButton(onClick = AppState.switchMaterialCallback) {
        Icon(imageVector = Icons.Default.Style, contentDescription = null)
    }
    IconButton(onClick = AppState.switchThemeCallback) {
        Icon(imageVector = Icons.Default.SettingsBrightness, contentDescription = null)
    }
}