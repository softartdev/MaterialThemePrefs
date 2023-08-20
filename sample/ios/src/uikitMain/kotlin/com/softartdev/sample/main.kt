package com.softartdev.sample

import androidx.compose.ui.main.defaultUIKitMain
import androidx.compose.ui.window.ComposeUIViewController
import com.softartdev.shared.App
import com.softartdev.themepref.MR
import dev.icerock.moko.resources.desc.desc

fun main() = defaultUIKitMain(
    executableName = MR.strings.material_theme_prefs.desc().localized(),
    rootViewController = ComposeUIViewController { App() }
)
