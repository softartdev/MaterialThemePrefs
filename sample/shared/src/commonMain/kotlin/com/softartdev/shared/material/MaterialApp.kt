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
    val screenState: AppState.Screen by remember(AppState::screenState) // provides composition locals
    when(screenState) {
        AppState.Screen.Settings -> SettingsBody()
        AppState.Screen.NoteDetail -> NoteDetailBody()
        AppState.Screen.Benchmark -> {
            println(" 🚧 Benchmark not realized for Material Design 2 yet, switch to Material Design 3")
            AppState.screenState.value = AppState.Screen.Settings
        }
    }
}
