package com.softartdev.theme.pref

import io.github.softartdev.theme_prefs.generated.resources.Res
import io.github.softartdev.theme_prefs.generated.resources.dark
import io.github.softartdev.theme_prefs.generated.resources.light
import io.github.softartdev.theme_prefs.generated.resources.system_default
import org.jetbrains.compose.resources.StringResource

/**
 * Theme option for light/dark/system appearance.
 *
 * @param stringRes Localized string resource for display (e.g. in theme picker).
 */
public enum class ThemeEnum(public val stringRes: StringResource) {
    Light(Res.string.light),
    Dark(Res.string.dark),
    SystemDefault(Res.string.system_default)
}
