package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

class DialogHolder {
    private var dialogContent: @Composable () -> Unit = {}
    private val showDialogState = mutableStateOf(false)

    fun showDialog(content: @Composable () -> Unit) {
        dialogContent = content
        showDialogState.value = true
    }

    @Composable
    fun showDialogIfNeed() {
        if (showDialogState.value) dialogContent()
    }

    fun dismissDialog() {
        showDialogState.value = false
    }
}