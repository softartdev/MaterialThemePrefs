package com.softartdev.themepref.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun CustomDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) = CustomDialog(
    title = title,
    text = text,
    buttons = {
        Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
            dismissButton()
            confirmButton()
        }
    },
    onDismissRequest = onDismissRequest
)

@Composable
fun CustomDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) = CustomDialogContent(
    title = title,
    text = text,
    buttons = buttons,
    onDismissRequest = onDismissRequest,
)
