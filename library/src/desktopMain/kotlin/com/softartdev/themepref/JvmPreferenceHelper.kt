package com.softartdev.themepref

import java.util.prefs.Preferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class JvmPreferenceHelper : PreferenceHelper {

     private var preferences: Preferences = Preferences.userNodeForPackage(ThemeEnum::class.java)

     override var themeEnum: ThemeEnum
          get() = preferences.getInt(THEME_KEY, ThemeEnum.SystemDefault.ordinal).let(ThemeEnum.values()::get)
          set(value) = preferences.putInt(THEME_KEY, value.ordinal)

     override fun clear() = preferences.clear()
}

@Composable
actual fun rememberPreferenceHelper(): PreferenceHelper = remember { JvmPreferenceHelper() }