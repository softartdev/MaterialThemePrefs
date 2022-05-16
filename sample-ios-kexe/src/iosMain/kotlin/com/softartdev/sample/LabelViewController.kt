package com.softartdev.sample

import com.sofartdev.sample.MR
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCOutlet
import platform.Foundation.NSCoder
import platform.UIKit.UILabel
import platform.UIKit.UIViewController
import platform.UIKit.UIViewControllerMeta

@ExportObjCClass
class LabelViewController : UIViewController {
    companion object Meta : UIViewControllerMeta()

    @ObjCOutlet
    lateinit var resourcesLabel: UILabel

    @OverrideInit
    constructor(coder: NSCoder) : super(coder)

    override fun viewDidLoad() {
        super.viewDidLoad()
        resourcesLabel.text = tryMR()
    }

    private fun tryMR(): String {
        val sb = StringBuilder().appendLine("➡️")
        try {
            val mr = MR
            sb.appendLine("mr = $mr")
            val strings = MR.strings
            sb.appendLine("strings = $strings")
//            val stringResource: StringResource = strings.settings
//            sb.appendLine("stringResource = $stringResource")
//            val desc: ResourceStringDesc = stringResource.desc()
            val desc: StringDesc = "TODO".desc()
            sb.appendLine("desc = $desc")
            val localized: String = desc.localized()
            sb.appendLine("localized = $localized")
        } catch (t: Throwable) {
            sb.appendLine("❌")
            sb.appendLine(t.stackTraceToString())
        }
        return sb.appendLine("⬅️").toString()
    }
}
