package com.softartdev.themepref

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
expect fun StringResource.composeLocalized(): String

@Composable
expect fun StringDesc.asString(): String