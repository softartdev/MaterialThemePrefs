@file:OptIn(ExperimentalMaterialApi::class, ExperimentalStdlibApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class, ExperimentalMaterialApi::class
)
@file:Suppress("EXPERIMENTAL_IS_NOT_ENABLED")

package com.softartdev.desktop

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.softartdev.themepref.ThemeEnum
import com.softartdev.themepref.MR
import com.softartdev.themepref.composeLocalized

@Stable
class DialogHolder {
    private var showDialog: Boolean by mutableStateOf(false)
    val dismissDialog = { showDialog = false }
    var dialogContent: @Composable () -> Unit = {}
    val showDialogIfNeed: @Composable () -> Unit = { if (showDialog) dialogContent() }

    private fun showDialog(content: @Composable () -> Unit) {
        dialogContent = content
        showDialog = true
    }

    fun showThemeChange(darkThemeState: MutableState<ThemeEnum>) = showDialog {
        ThemeDialog(darkThemeState, dismissDialog)
    }
}

@Composable
fun ThemeDialog(
    darkThemeState: MutableState<ThemeEnum> = mutableStateOf(ThemeEnum.SystemDefault),
    dismissDialog: () -> Unit = {}
) = AlertDialog(
    onDismissRequest = dismissDialog,
    title = { Text(MR.strings.choose_theme.composeLocalized()) },
    text = {
        RadioDialogContent(darkThemeState)
    },
    confirmButton = { Button(onClick = dismissDialog) { Text(MR.strings.ok.composeLocalized()) } },
    dismissButton = { Button(onClick = dismissDialog) { Text(MR.strings.cancel.composeLocalized()) } },
)

@Composable
fun RadioDialogContent(darkThemeState: MutableState<ThemeEnum>) {
//    val radioOptions: Collection<String> = ThemeEnum.values().map(ThemeEnum::toLocalizedString)
//    val (selectedOption: String, onOptionSelected: (String) -> Unit) = remember { mutableStateOf(radioOptions.first()) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(Modifier.selectableGroup()) {
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
}

@Preview
@Composable
fun PreviewErrorDialog() = PreviewDialog { ThemeDialog() }

@Preview
@Composable
fun PreviewDialog(dialogContent: @Composable () -> Unit) =
    Box(modifier = Modifier.fillMaxSize()) { dialogContent() }
