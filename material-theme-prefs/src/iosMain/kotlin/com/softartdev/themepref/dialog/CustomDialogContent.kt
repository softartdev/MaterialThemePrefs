package com.softartdev.themepref.dialog

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun CustomDialogContent(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    buttons: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
) = Popup(
    popupPositionProvider = CustomPopupPositionProvider,
    focusable = true,
    onDismissRequest = onDismissRequest,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(onDismissRequest) {
                detectTapGestures(onPress = { onDismissRequest() })
            },
        contentAlignment = Alignment.Center
    ) {
        Surface(
            elevation = 24.dp,
            modifier = modifier.align(Alignment.Center).padding(horizontal = 24.dp),
        ) {
            Column {
                Box(modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        ProvideTextStyle(MaterialTheme.typography.subtitle1, title)
                    }
                }
                Box(modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        ProvideTextStyle(MaterialTheme.typography.body2, text)
                    }
                }
                Box(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp).align(Alignment.End)) {
                    buttons()
                }
            }
        }
    }
}
