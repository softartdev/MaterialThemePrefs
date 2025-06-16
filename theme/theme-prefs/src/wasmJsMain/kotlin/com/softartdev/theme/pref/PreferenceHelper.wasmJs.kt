package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import kotlinx.browser.window
import org.w3c.dom.Storage

internal class WasmJsPreferenceHelper : PreferenceHelper {

    private val storage: Storage = window.localStorage

    override var themeEnum: ThemeEnum
        get() = storage.getItem(THEME_KEY)?.toIntOrNull()
            ?.let(ThemeEnum.entries::getOrNull)
            ?: ThemeEnum.SystemDefault
        set(value) = storage.setItem(THEME_KEY, value.ordinal.toString())

    override fun clear() = storage.removeItem(THEME_KEY)
}

@Composable
internal actual fun obtainPreferenceHelper(): PreferenceHelper = WasmJsPreferenceHelper()