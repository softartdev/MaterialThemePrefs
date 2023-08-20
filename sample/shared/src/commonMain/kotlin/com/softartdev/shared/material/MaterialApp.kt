package com.softartdev.shared.material

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.softartdev.shared.AppState
import com.softartdev.theme.material.PreferableMaterialTheme
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun MaterialApp() = PreferableMaterialTheme { // provides composition locals
    val showNote: Boolean by remember(AppState::showNote)
    when(showNote) {
        true -> NoteDetailBody()
        else -> SettingsBody()
    }
}
