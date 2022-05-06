package com.softartdev.themepref

import androidx.compose.runtime.Composable
import platform.Foundation.NSUserDefaults

class IosPreferenceHelper : PreferenceHelper {

    private var preferences: NSUserDefaults = NSUserDefaults.standardUserDefaults

    override var themeEnum: ThemeEnum
        get() = preferences.integerForKey(defaultName = THEME_KEY).toInt().let(ThemeEnum.values()::get)
        set(value) = preferences.setInteger(value.ordinal.toLong(), THEME_KEY)

    override fun clear() = preferences.removeObjectForKey(defaultName = THEME_KEY)
}

@Composable
actual fun obtainPreferenceHelper(): PreferenceHelper = IosPreferenceHelper()