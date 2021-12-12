package com.softartdev.themepref

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.preference.PreferenceManager

class AndroidPreferenceHelper(context: Context) : PreferenceHelper {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

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