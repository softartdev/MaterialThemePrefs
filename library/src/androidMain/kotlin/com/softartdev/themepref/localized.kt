package com.softartdev.themepref

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

@Composable
actual fun StringResource.composeLocalized(): String = stringResource(id = this.resourceId)

@Composable
actual fun StringDesc.asString(): String = toString(context = LocalContext.current)