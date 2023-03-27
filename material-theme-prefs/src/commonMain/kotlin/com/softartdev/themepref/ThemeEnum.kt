package com.softartdev.themepref

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.compose.stringResource

enum class ThemeEnum {
    Light,
    Dark,
    SystemDefault;

    @Composable
    fun toLocalizedString(): String = stringResource(
        resource = when (this) {
            Light -> MR.strings.light
            Dark -> MR.strings.dark
            SystemDefault -> MR.strings.system_default
        }
    )
}