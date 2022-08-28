package com.softartdev.sample

import androidx.compose.ui.window.Application
import com.softartdev.shared.App
import com.softartdev.themepref.MR
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.ObjCObjectBase
import platform.UIKit.*

class SkikoAppDelegate : UIResponder, UIApplicationDelegateProtocol {
    companion object : UIResponderMeta(), UIApplicationDelegateProtocolMeta

    @ObjCObjectBase.OverrideInit
    constructor() : super()

    private var _window: UIWindow? = null
    override fun window() = _window
    override fun setWindow(window: UIWindow?) {
        _window = window
    }

    override fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any?, *>?
    ): Boolean {
        window = UIWindow(frame = UIScreen.mainScreen.bounds)
        window!!.rootViewController = Application(
            title = MR.strings.material_theme_prefs.desc().localized()
        ) {
            App()
        }
        window!!.makeKeyAndVisible()
        return true
    }
}
