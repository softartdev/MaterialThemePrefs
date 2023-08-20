package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface PreferenceHelper {

    var themeEnum: ThemeEnum

    fun clear()
}

const val THEME_KEY: String = "theme_key"

@Composable
expect fun obtainPreferenceHelper(): PreferenceHelper

@Composable
fun rememberPreferenceHelper(): PreferenceHelper {
    val preferenceHelper = obtainPreferenceHelper()
    return remember { preferenceHelper }
}