package com.softartdev.theme.pref

import androidx.compose.runtime.Composable
import java.util.prefs.Preferences

internal class JvmPreferenceHelper : PreferenceHelper {

     private var preferences: Preferences = Preferences.userNodeForPackage(ThemeEnum::class.java)

     override var themeEnum: ThemeEnum
          get() = preferences.getInt(THEME_KEY, ThemeEnum.SystemDefault.ordinal).let(ThemeEnum.entries::get)
          set(value) = preferences.putInt(THEME_KEY, value.ordinal)

     override fun clear() = preferences.clear()
}

@Composable
internal actual fun obtainPreferenceHelper(): PreferenceHelper = JvmPreferenceHelper()