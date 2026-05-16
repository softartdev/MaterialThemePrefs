@file:OptIn(ExperimentalComposeUiApi::class)

package com.softartdev.shared

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.input.PlatformImeOptions

@Composable
actual fun EnableEdgeToEdge(material3: Boolean, inDark: Boolean) {
}

actual fun platformKeyboardOptions(): KeyboardOptions = KeyboardOptions(
    platformImeOptions = PlatformImeOptions { usingNativeTextInput(true) }
)
