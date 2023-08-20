package com.softartdev.shared

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import com.softartdev.themepref.PreferableMaterialTheme
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun App() = PreferableMaterialTheme { // provides composition locals
    var showNote: Boolean by remember { mutableStateOf(false) }
    val onBackClickListener: () -> Unit = { showNote = !showNote }
    val noteTextState: MutableState<String> = remember { mutableStateOf(readMe) }
    val noteScrollState = rememberScrollState()
    when(showNote) {
        true -> NoteDetailBody(noteTextState, onBackClickListener, scrollState = noteScrollState)
        else -> SettingsBody(onBackClick = onBackClickListener)
    }
}
