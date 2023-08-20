package com.softartdev.shared.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.softartdev.shared.AppState
import com.softartdev.theme.material3.PreferableMaterialTheme
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun Material3App() = PreferableMaterialTheme { // provides composition locals
    val showNote: Boolean by remember(AppState::showNote) // provides composition locals
    when(showNote) {
        true -> NoteDetailBody()
        else -> SettingsBody()
    }
}
