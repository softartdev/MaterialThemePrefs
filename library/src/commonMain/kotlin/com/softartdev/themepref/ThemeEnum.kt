package com.softartdev.themepref

import androidx.compose.runtime.Composable

enum class ThemeEnum {
    Light,
    Dark,
    SystemDefault;

    @Composable
    fun toLocalizedString(): String = when (this) {
        Light -> MR.strings.light
        Dark -> MR.strings.dark
        SystemDefault -> MR.strings.system_default
    }.composeLocalized()
}