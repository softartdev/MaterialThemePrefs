package com.softartdev.sample

import androidx.compose.ui.main.defaultUIKitMain
import androidx.compose.ui.window.Application
import com.softartdev.shared.App
import com.softartdev.themepref.MR
import dev.icerock.moko.resources.desc.desc

fun main() = defaultUIKitMain(
    executableName = "SampleApp",
    rootViewController = Application(
        title = MR.strings.material_theme_prefs.desc().localized(),
        content = { App() }
    )
)
