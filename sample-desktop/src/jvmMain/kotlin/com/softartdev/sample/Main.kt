package com.softartdev.sample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.softartdev.themepref.App

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}