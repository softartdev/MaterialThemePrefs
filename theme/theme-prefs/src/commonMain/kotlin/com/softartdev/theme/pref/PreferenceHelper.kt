package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

public interface PreferenceHelper {

    public var themeEnum: ThemeEnum

    public fun clear()
}

internal const val THEME_KEY: String = "theme_key"

@Composable
internal expect fun obtainPreferenceHelper(): PreferenceHelper

@Composable
public fun rememberPreferenceHelper(): PreferenceHelper {
    val preferenceHelper = obtainPreferenceHelper()
    return remember { preferenceHelper }
}