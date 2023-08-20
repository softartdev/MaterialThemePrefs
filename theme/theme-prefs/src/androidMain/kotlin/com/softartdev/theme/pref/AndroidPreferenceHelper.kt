package com.softartdev.theme.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AndroidPreferenceHelper(context: Context) : PreferenceHelper {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(context.packageName + "_ThemePref", Context.MODE_PRIVATE)

    override var themeEnum: ThemeEnum
        get() = preferences.getInt(THEME_KEY, ThemeEnum.SystemDefault.ordinal).let(ThemeEnum.values()::get)
        set(value) = preferences.edit().putInt(THEME_KEY, value.ordinal).apply()

    override fun clear() = preferences.edit().clear().apply()
}

@Composable
actual fun obtainPreferenceHelper(): PreferenceHelper {
    val context = LocalContext.current.applicationContext
    return AndroidPreferenceHelper(context)
}