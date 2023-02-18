package com.softartdev.themepref

import androidx.compose.runtime.Composable
import com.softartdev.themepref.dialog.CustomDialog

@Composable
actual fun AlertDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) = CustomDialog(
    title = title,
    text = text,
    confirmButton = confirmButton,
    dismissButton = dismissButton,
    onDismissRequest = onDismissRequest
)

@Composable
actual fun AlertDialog(
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) = CustomDialog(
    title = title,
    text = text,
    buttons = buttons,
    onDismissRequest = onDismissRequest,
)