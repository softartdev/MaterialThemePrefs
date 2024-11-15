package com.softartdev.shared.material

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.softartdev.shared.AppNavGraph
import com.softartdev.theme.material.PreferableMaterialTheme
import com.softartdev.theme.material.ThemeDialog
import com.softartdev.theme.pref.PreferenceHelper
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun MaterialApp(
    navController: NavHostController = rememberNavController()
) = PreferableMaterialTheme { // provides composition locals
    NavHost(
        navController = navController,
        startDestination = navController.currentDestination?.route ?: AppNavGraph.Settings.name,
        route = "MaterialApp"
    ) {
        composable(route = AppNavGraph.Settings.name) {
            SettingsBody(
                onBackClick = { navController.navigate(route = AppNavGraph.NoteDetail.name) },
                onThemeClick = { navController.navigate(route = AppNavGraph.ThemeDialog.name) }
            )
        }
        composable(route = AppNavGraph.NoteDetail.name) {
            NoteDetailBody(
                onBackClick = { navController.navigate(route = AppNavGraph.Settings.name) }
            )
        }
        dialog(route = AppNavGraph.ThemeDialog.name) {
            val preferenceHelper: PreferenceHelper = themePrefs.preferenceHelper
            ThemeDialog(
                darkThemeState = themePrefs.darkThemeState,
                writePref = preferenceHelper::themeEnum::set,
                dismissDialog = navController::navigateUp,
            )
        }
    }
}
