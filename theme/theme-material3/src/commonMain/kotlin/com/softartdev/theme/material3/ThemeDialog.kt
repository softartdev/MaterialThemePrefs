package com.softartdev.theme.material3

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.DialogHolder
import com.softartdev.theme.pref.MR
import com.softartdev.theme.pref.ThemeEnum
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun ThemeDialog(
    darkThemeState: MutableState<ThemeEnum> = mutableStateOf(ThemeEnum.SystemDefault),
    writePref: (ThemeEnum) -> Unit = {},
    dismissDialog: () -> Unit = {}
) {
    val previousState = remember { darkThemeState.value }
    AlertDialog(
        onDismissRequest = dismissDialog,
        title = { Text(stringResource(MR.strings.choose_theme)) },
        text = { RadioDialogContent(darkThemeState) },
        confirmButton = {
            Button(onClick = {
                writePref(darkThemeState.value)
                dismissDialog()
            }) { Text(stringResource(MR.strings.ok)) }
        },
        dismissButton = {
            Button(onClick = {
                darkThemeState.value = previousState
                dismissDialog()
            }) { Text(stringResource(MR.strings.cancel)) }
        },
    )
}

fun DialogHolder.showThemeChange(darkThemeState: MutableState<ThemeEnum>, writePref: (ThemeEnum) -> Unit) = showDialog {
    ThemeDialog(darkThemeState, writePref, ::dismissDialog)
}