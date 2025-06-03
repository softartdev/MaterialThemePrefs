package com.softartdev.theme.material

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.softartdev.theme.pref.PreferableMaterialTheme.themePrefs
import com.softartdev.theme.pref.ThemeEnum
import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.cancel
import io.github.softartdev.theme_prefs.generated.resources.choose_theme
import io.github.softartdev.theme_prefs.generated.resources.ok
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * AlertDialog for selecting a theme.
 * Should use directly in the composable destination that wants to show that dialog.
 *
 * @param darkThemeState MutableState of the current theme.
 * @param writePref Function to write the selected theme to preferences.
 * @param dismissDialog Function to dismiss the dialog.
 */
@Composable
public fun ThemeAlertDialog(
    darkThemeState: MutableState<ThemeEnum> = themePrefs.darkThemeState,
    writePref: (ThemeEnum) -> Unit = themePrefs.preferenceHelper.let { it::themeEnum::set },
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

/**
 * Dialog layout for selecting a theme. Represents a separate screen.
 * Should use in the dialog destination that will be hosted within a [androidx.compose.ui.window.Dialog].
 *
 * @param darkThemeState MutableState of the current theme.
 * @param writePref Function to write the selected theme to preferences.
 * @param dismissDialog Function to dismiss the dialog.
 */
@Composable
public fun ThemeDialogContent(
    darkThemeState: MutableState<ThemeEnum> = themePrefs.darkThemeState,
    writePref: (ThemeEnum) -> Unit = themePrefs.preferenceHelper.let { it::themeEnum::set },
    dismissDialog: () -> Unit = {}
) {
    val previousState = remember { darkThemeState.value }
    MaterialAlertDialogContent(
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

/**
 * [Material Design alert dialog](https://material.io/components/dialogs#alert-dialog) content
 *
 * This composable provides the content layout for a Material alert dialog without creating
 * the actual dialog itself. It's useful when you want to customize how the dialog is shown
 * or integrate it with different dialog containers.
 *
 * Alert dialogs interrupt users with urgent information, details, or actions.
 *
 * The dialog will position its buttons based on the available space. By default it will try to
 * place them horizontally next to each other and fallback to vertical placement if not enough
 * space is available.
 *
 * @param modifier Modifier to be applied to the layout of the dialog content surface.
 * @param title The title of the Dialog which should specify the purpose of the Dialog. The title is
 *   not mandatory, because there may be sufficient information inside the [text]. Provided text
 *   style will be [Typography.subtitle1].
 * @param text The text which presents the details regarding the Dialog's purpose. Provided text
 *   style will be [Typography.body2].
 * @param confirmButton A button which is meant to confirm a proposed action. The dialog does not
 *   set up any events for this button so they need to be set up by the caller.
 * @param dismissButton A button which is meant to dismiss the dialog. The dialog does not set up
 *   any events for this button so they need to be set up by the caller.
 * @param shape Defines the Dialog's shape.
 * @param backgroundColor The background color of the dialog surface.
 * @param contentColor The preferred content color provided by this dialog surface to its children.
 */
@Composable
public fun MaterialAlertDialogContent(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
) {
    Surface(
        modifier = modifier.width(320.dp),
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Column(modifier = Modifier.padding(top = 24.dp, start = 24.dp)) {
            title?.let {
                Box(Modifier.padding(bottom = 0.dp).align(Alignment.Start)) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                            it()
                        }
                    }
                }
            }
            text?.let {
                Box(
                    Modifier
                        .weight(1f, fill = false)
                        .padding(bottom = 24.dp)
                        .align(Alignment.Start)
                ) {
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        ProvideTextStyle(MaterialTheme.typography.body2) {
                            it()
                        }
                    }
                }
            }
            Box(Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    dismissButton?.invoke()
                    Spacer(Modifier.width(8.dp))
                    confirmButton()
                }
            }
        }
    }
}

@Preview
@Composable
public fun ThemeDialogPreview() {
    PreferableMaterialTheme { ThemeAlertDialog() }
}

@Preview
@Composable
public fun ThemeDialogContentPreview() {
    PreferableMaterialTheme { ThemeDialogContent() }
}

