package com.softartdev.theme.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
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
    val previousState: ThemeEnum = remember { darkThemeState.value }
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
    val previousState: ThemeEnum = remember { darkThemeState.value }
    Material3AlertDialogContent(
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
 * [Material Design basic dialog](https://m3.material.io/components/dialogs/overview) content
 *
 * This composable provides the content layout for a Material3 alert dialog without creating
 * the actual dialog itself. It's useful when you want to customize how the dialog is shown
 * or integrate it with different dialog containers.
 *
 * Dialogs provide important prompts in a user flow. They can require an action, communicate
 * information, or help users accomplish a task.
 *
 * @param modifier the [Modifier] to be applied to this dialog content surface
 * @param title optional composable that displays the title of the dialog
 * @param text optional composable that displays the main text content of the dialog
 * @param confirmButton button which is meant to confirm a proposed action. The dialog does not
 *   set up any events for this button so they need to be set up by the caller.
 * @param dismissButton optional button which is meant to dismiss the dialog. The dialog does not
 *   set up any events for this button so they need to be set up by the caller.
 * @param shape defines the shape of this dialog's container surface
 * @param containerColor the color used for the background of this dialog surface
 * @param titleContentColor the content color used for the title
 * @param textContentColor the content color used for the text
 * @param tonalElevation when [containerColor] is [ColorScheme.surface], a translucent primary color
 *   overlay is applied on top of the container. A higher tonal elevation value will result in a
 *   darker color in light theme and lighter color in dark theme.
 */
@Composable
public fun Material3AlertDialogContent(
    modifier: Modifier = Modifier,
    title: (@Composable BoxScope.() -> Unit)? = null,
    text: (@Composable BoxScope.() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    shape: Shape = AlertDialogDefaults.shape,
    containerColor: Color = AlertDialogDefaults.containerColor,
    titleContentColor: Color = AlertDialogDefaults.titleContentColor,
    textContentColor: Color = AlertDialogDefaults.textContentColor,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
) {
    Surface(
        modifier = modifier.width(320.dp),
        shape = shape,
        color = containerColor,
        tonalElevation = tonalElevation,
    ) {
        Column(modifier = Modifier.padding(all = 24.dp)) {
            title?.let {
                ProvideContentColorTextStyle(
                    contentColor = titleContentColor,
                    textStyle = MaterialTheme.typography.headlineSmall
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .align(Alignment.Start),
                        content = it
                    )
                }
            }
            text?.let {
                ProvideContentColorTextStyle(
                    contentColor = textContentColor,
                    textStyle = MaterialTheme.typography.bodyMedium
                ) {
                    Box(
                        modifier = Modifier
                            .weight(weight = 1f, fill = false)
                            .padding(bottom = 24.dp)
                            .align(Alignment.Start),
                        content = it
                    )
                }
            }
            Box(modifier = Modifier.align(Alignment.End)) {
                ProvideContentColorTextStyle(
                    contentColor = MaterialTheme.colorScheme.primary,
                    textStyle = MaterialTheme.typography.labelLarge,
                    content = {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            dismissButton?.invoke()
                            confirmButton()
                        }
                    }
                )
            }
        }
    }
}

@Composable
internal fun ProvideContentColorTextStyle(
    contentColor: Color,
    textStyle: TextStyle,
    content: @Composable () -> Unit
) {
    val mergedStyle = LocalTextStyle.current.merge(textStyle)
    CompositionLocalProvider(
        LocalContentColor provides contentColor,
        LocalTextStyle provides mergedStyle,
        content = content
    )
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

