package com.softartdev.themepref

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
actual fun StringResource.composeLocalized(): String = this.localized()

@Composable
actual fun StringDesc.asString(): String = this.localized()