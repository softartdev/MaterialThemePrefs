package com.softartdev.theme.material

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.softartdev.theme.pref.ThemeEnum
import org.jetbrains.compose.resources.stringResource

@Composable
fun RadioDialogContent(darkThemeState: MutableState<ThemeEnum>) = Column(Modifier.selectableGroup()) {
    ThemeEnum.entries.forEach { themeEnum: ThemeEnum ->
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
                text = stringResource(themeEnum.stringRes),
                style = MaterialTheme.typography.body1.merge(),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}