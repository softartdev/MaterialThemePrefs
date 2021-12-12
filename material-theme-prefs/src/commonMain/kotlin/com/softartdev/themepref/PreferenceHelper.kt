package com.softartdev.themepref

import androidx.compose.runtime.Composable

interface PreferenceHelper {

    var themeEnum: ThemeEnum

    fun clear()
}

const val THEME_KEY: String = "theme_key"

@Composable
expect fun obtainPreferenceHelper(): PreferenceHelper