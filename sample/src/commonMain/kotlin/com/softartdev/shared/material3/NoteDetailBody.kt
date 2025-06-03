@file:OptIn(ExperimentalMaterial3Api::class)

package com.softartdev.shared.material3

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.softartdev.shared.AppState
import com.softartdev.theme.material.PreferableMaterialTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun NoteDetailBody(
    textState: MutableState<String> = remember(AppState::textState),
    onBackClick: () -> Unit = {},
    scrollState: ScrollState = rememberSaveable(
        saver = ScrollState.Saver,
        init = AppState::scrollState
    ),
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "Material Theme Prefs", maxLines = 1, overflow = TextOverflow.Ellipsis) },
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
    }) { paddingValues ->
    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = AppState::scrollProgress,
            drawStopIndicator = {},
        )
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

@Preview
@Composable
fun NoteDetailBodyPreview() {
    PreferableMaterialTheme { NoteDetailBody() }
}