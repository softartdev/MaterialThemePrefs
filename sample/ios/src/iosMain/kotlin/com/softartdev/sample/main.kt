package com.softartdev.sample

import androidx.compose.ui.main.defaultUIKitMain
import androidx.compose.ui.window.ComposeUIViewController
import com.softartdev.shared.App
import com.softartdev.theme.pref.MR
import dev.icerock.moko.resources.desc.Utils

fun main() = defaultUIKitMain(
    //TODO MR.strings.material_theme_prefs.desc().localized()
    executableName = Utils.localizedString(MR.strings.material_theme_prefs),
    rootViewController = ComposeUIViewController { App() }
)
