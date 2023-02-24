package com.softartdev.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.softartdev.themepref.LocalThemePrefs
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun NoteDetailBody(
    textState: MutableState<String> = mutableStateOf("Text"),
    onBackClick: () -> Unit = {},
    showLoading: Boolean = true,
) = Scaffold(
    topBar = {
        TopAppBar(
            title = { Text(text = "Title", maxLines = 1) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = Icons.Default.ArrowBack.name
                    )
                }
            },
        )
    }) {
    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (showLoading) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier.weight(1F).fillMaxWidth().padding(8.dp),
                label = { Text("type_text") },
            )
        }
        LocalThemePrefs.current.showDialogIfNeed()
    }
}