package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DialogHolder {
    private var dialogContent: @Composable () -> Unit = {}
    private var showDialog: Boolean by mutableStateOf(false)

    fun showDialog(content: @Composable () -> Unit) {
        dialogContent = content
        showDialog = true
    }

    @Composable
    fun showDialogIfNeed() {
        if (showDialog) dialogContent()
    }

    fun dismissDialog() {
        showDialog = false
    }
}