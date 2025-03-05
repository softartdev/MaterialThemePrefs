package com.softartdev.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.softartdev.shared.material.MaterialApp
import com.softartdev.shared.material3.Material3App
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun App(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val showMaterial3: Boolean by remember(AppState::showMaterial3)
    when(showMaterial3) {
        true -> Material3App(modifier, navController)
        else -> MaterialApp(modifier, navController)
    }
}
