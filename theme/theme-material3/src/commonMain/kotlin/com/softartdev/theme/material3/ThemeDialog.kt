package com.softartdev.theme.material3

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.softartdev.theme.pref.ThemeEnum
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.cancel
import io.github.softartdev.theme_prefs.generated.resources.choose_theme
import io.github.softartdev.theme_prefs.generated.resources.ok
import org.jetbrains.compose.resources.stringResource

@Composable
public fun ThemeDialog(
    darkThemeState: MutableState<ThemeEnum> = mutableStateOf(ThemeEnum.SystemDefault),
    writePref: (ThemeEnum) -> Unit = {},
    dismissDialog: () -> Unit = {}
) {
    val previousState = remember { darkThemeState.value }
    AlertDialog(
        onDismissRequest = dismissDialog,
        title = { Text(stringResource(Res.string.choose_theme)) },
        text = { RadioDialogContent(darkThemeState) },
        confirmButton = {
            Button(onClick = {
                writePref(darkThemeState.value)
                dismissDialog()
            }) { Text(stringResource(Res.string.ok)) }
        },
        dismissButton = {
            Button(onClick = {
                darkThemeState.value = previousState
                dismissDialog()
            }) { Text(stringResource(Res.string.cancel)) }
        },
    )
}
