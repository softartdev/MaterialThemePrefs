package com.softartdev.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.softartdev.themepref.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}