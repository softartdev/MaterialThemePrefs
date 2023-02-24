package com.softartdev.shared

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
    val noteTextState: MutableState<String> = mutableStateOf("Text")
    when(showNote) {
        true -> NoteDetailBody(onBackClick = onBackClickListener, textState = noteTextState)
        else -> SettingsBody(onBackClick = onBackClickListener)
    }
}