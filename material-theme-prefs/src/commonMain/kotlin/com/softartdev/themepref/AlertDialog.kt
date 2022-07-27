package com.softartdev.themepref

import androidx.compose.runtime.Composable

@Composable
expect fun AlertDialog(
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)?,
    onDismissRequest: () -> Unit
)

@Composable
expect fun AlertDialog(
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit
)