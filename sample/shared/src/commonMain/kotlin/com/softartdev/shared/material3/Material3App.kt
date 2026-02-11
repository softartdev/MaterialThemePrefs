package com.softartdev.shared.material3

import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.softartdev.shared.AppNavGraph
import com.softartdev.shared.DesignNavGraph
import com.softartdev.shared.EnableEdgeToEdge
import com.softartdev.shared.inDark
import com.softartdev.theme.material3.PreferableMaterialTheme
import com.softartdev.theme.material3.ThemeDialogContent
import kotlin.experimental.ExperimentalObjCRefinement
import kotlin.native.HiddenFromObjC

@OptIn(ExperimentalObjCRefinement::class)
@HiddenFromObjC
@Composable
fun Material3App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) = PreferableMaterialTheme { // provides composition locals
    EnableEdgeToEdge(material3 = true, inDark = themePrefs.inDark)
    NavHost(
        modifier = modifier.imePadding(),
        navController = navController,
        startDestination = AppNavGraph.from(navController.currentDestination?.route),
        route = DesignNavGraph.Material3App::class
    ) {
        composable<AppNavGraph.Settings> {
            SettingsBody(
                onBackClick = { navController.navigate(route = AppNavGraph.NoteDetail) },
                onThemeClick = { navController.navigate(route = AppNavGraph.ThemeDialog) }
            )
        }
        composable<AppNavGraph.NoteDetail> {
            NoteDetailBody(
                onBackClick = { navController.navigate(route = AppNavGraph.Settings) }
            )
        }
        dialog<AppNavGraph.ThemeDialog> {
            ThemeDialogContent(dismissDialog = navController::popBackStack)
        }
    }
}
