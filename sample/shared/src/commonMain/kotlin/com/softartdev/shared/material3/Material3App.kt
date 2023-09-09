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
    val screenState: AppState.Screen by remember(AppState::screenState) // provides composition locals
    when(screenState) {
        AppState.Screen.Settings -> SettingsBody()
        AppState.Screen.NoteDetail -> NoteDetailBody()
        AppState.Screen.Benchmark -> BenchmarkBody()
    }
}
