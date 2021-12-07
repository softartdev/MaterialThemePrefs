@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalStdlibApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)
@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.softartdev.themepref

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Stable
class DialogHolder {
    var showDialog: Boolean by mutableStateOf(false)
    val dismissDialog = { showDialog = false }
    var dialogContent: @Composable () -> Unit = {}
    val showDialogIfNeed: @Composable () -> Unit = { if (showDialog) dialogContent() }

    fun showDialog(content: @Composable () -> Unit) {
        dialogContent = content
        showDialog = true
    }

    fun showThemeChange(darkThemeState: MutableState<ThemeEnum>, writePref: (ThemeEnum) -> Unit) = showDialog {
        ThemeDialog(darkThemeState, writePref, dismissDialog)
    }
}

@Composable
fun ThemeDialog(
    darkThemeState: MutableState<ThemeEnum> = mutableStateOf(ThemeEnum.SystemDefault),
    writePref: (ThemeEnum) -> Unit = {},
    dismissDialog: () -> Unit = {}
) {
    val previousState = remember { darkThemeState.value }
    AlertDialog(
        onDismissRequest = dismissDialog,
        title = { Text(MR.strings.choose_theme.composeLocalized()) },
        text = { RadioDialogContent(darkThemeState) },
        confirmButton = { Button(onClick = {
            writePref(darkThemeState.value)
            dismissDialog()
        }) { Text(MR.strings.ok.composeLocalized()) } },
        dismissButton = { Button(onClick = {
            darkThemeState.value = previousState
            dismissDialog()
        }) { Text(MR.strings.cancel.composeLocalized()) } },
    )
}

@Composable
fun RadioDialogContent(darkThemeState: MutableState<ThemeEnum>) = Column(Modifier.selectableGroup()) {
    ThemeEnum.values().forEach { themeEnum: ThemeEnum ->
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectable(
                    selected = themeEnum == darkThemeState.value,
                    onClick = { darkThemeState.value = themeEnum },
                    role = Role.RadioButton
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = themeEnum == darkThemeState.value,
                onClick = null // null recommended for accessibility with screenreaders
            )
            Text(
                text = themeEnum.toLocalizedString(),
                style = MaterialTheme.typography.body1.merge(),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
