package com.softartdev.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Application
import com.softartdev.themepref.MR
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.ResourceStringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.*
import platform.Foundation.NSStringFromClass
import platform.UIKit.*

fun main() {
    val args = emptyArray<String>()
    memScoped {
        val argc = args.size + 1
        val argv = (arrayOf("skikoApp") + args).map { it.cstr.ptr }.toCValues()
        autoreleasepool {
            UIApplicationMain(argc, argv, null, NSStringFromClass(SkikoAppDelegate))
        }
    }
}

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
        window!!.rootViewController = Application("Minesweeper") {
//            App() //TODO fix crash
            SampleApp()
        }
        window!!.makeKeyAndVisible()
        return true
    }
}

@Composable
fun SampleApp() = MaterialTheme(colors = darkColors()) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Sample") })
    }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Hello, World! 🦄", style = MaterialTheme.typography.body1)
            Text(text = tryMR(), style = MaterialTheme.typography.body2)
        }
    }
}

fun tryMR(): String {
    val sb = StringBuilder().appendLine("➡️")
    try {
        val mr = MR
        sb.appendLine("mr = $mr")
        val strings = MR.strings
        sb.appendLine("strings = $strings")
        val stringResource: StringResource = strings.settings
        sb.appendLine("stringResource = $stringResource")
        val desc: ResourceStringDesc = stringResource.desc()
        sb.appendLine("desc = $desc")
        val localized: String = desc.localized()
        sb.appendLine("localized = $localized")
    } catch (t: Throwable) {
        sb.appendLine("❌")
        sb.appendLine(t.stackTraceToString())
    }
    return sb.appendLine("⬅️").toString()
}
