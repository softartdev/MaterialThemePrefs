package com.softartdev.shared

import android.app.Activity
import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.LocalActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat

@Composable
actual fun EnableEdgeToEdge(material3: Boolean, inDark: Boolean) {
    val activity: Activity = LocalActivity.current ?: return
    if (material3) {
        val componentActivity = activity as? ComponentActivity ?: return
        val scrimLight: Int = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
        val scrimDark: Int = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
        val sbStyle: SystemBarStyle = when {
            inDark -> SystemBarStyle.dark(scrim = scrimDark)
            else -> SystemBarStyle.light(scrim = scrimLight, darkScrim = scrimDark)
        }
        componentActivity.enableEdgeToEdge(statusBarStyle = sbStyle, navigationBarStyle = sbStyle)
    } else {
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
    }
}
