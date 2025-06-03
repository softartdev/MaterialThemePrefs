package com.softartdev.shared

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Material Theme Prefs",
        state = rememberWindowState(width = 340.dp, height = 500.dp),
    ) {
        App()
    }
}