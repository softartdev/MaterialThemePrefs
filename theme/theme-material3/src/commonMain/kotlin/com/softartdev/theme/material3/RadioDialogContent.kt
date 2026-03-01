package com.softartdev.theme.material3

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softartdev.theme.pref.ThemeEnum
import org.jetbrains.compose.resources.stringResource

/**
 * Radio group for selecting a [ThemeEnum] (Light, Dark, System default). Use inside theme dialogs.
 *
 * @param themeState State holding the current selection; updates on option click.
 */
@Composable
public fun RadioDialogContent(themeState: MutableState<ThemeEnum>) {
    Column(modifier = Modifier.selectableGroup()) {
        ThemeEnum.entries.forEach { themeEnum: ThemeEnum ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = themeEnum == themeState.value,
                        onClick = { themeState.value = themeEnum },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = themeEnum == themeState.value,
                    onClick = null // null recommended for accessibility with screenreaders
                )
                Text(
                    text = stringResource(themeEnum.stringRes),
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RadioDialogContentPreview() {
    PreferableMaterialTheme {
        RadioDialogContent(themeState = mutableStateOf(ThemeEnum.SystemDefault))
    }
}