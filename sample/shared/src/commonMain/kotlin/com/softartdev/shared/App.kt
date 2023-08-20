package com.softartdev.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.softartdev.shared.material.MaterialApp
import com.softartdev.shared.material3.Material3App
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun App() {
    val showMaterial3: Boolean by remember(AppState::showMaterial3)
    when(showMaterial3) {
        true -> Material3App()
        else -> MaterialApp()
    }
}
