package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import platform.Foundation.NSUserDefaults

internal class IosPreferenceHelper : PreferenceHelper {

    private var preferences: NSUserDefaults = NSUserDefaults.standardUserDefaults

    override var themeEnum: ThemeEnum
        get() = preferences.integerForKey(defaultName = THEME_KEY).toInt().let(ThemeEnum.values()::get)
        set(value) = preferences.setInteger(value.ordinal.toLong(), THEME_KEY)

    override fun clear() = preferences.removeObjectForKey(defaultName = THEME_KEY)
}

@Composable
internal actual fun obtainPreferenceHelper(): PreferenceHelper = IosPreferenceHelper()