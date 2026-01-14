package com.ahanafrifat.yourplants.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahanafrifat.yourplants.enhos.presentation.create_echo.CreateEchoRoot
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosRoot
import com.ahanafrifat.yourplants.enhos.presentation.settings.SettingsRoot
import com.ahanafrifat.yourplants.enhos.presentation.util.toCreateEchoRoute

@Composable
fun NavigationRoot(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.Echos
    ) {
        composable<NavigationRoute.Echos> {
            EchosRoot(
                onNavigationToCreateEcho = { recordingDetails ->
                    navHostController.navigate(recordingDetails.toCreateEchoRoute())
                },
                onNavigationToSettings = {
                    navHostController.navigate(NavigationRoute.Settings)
                }
            )
        }
        composable<NavigationRoute.CreateEcho> {
            CreateEchoRoot(
                onConfirmLeave = navHostController::navigateUp
            )
        }
        composable<NavigationRoute.Settings> {
            SettingsRoot(
                onGoBack = navHostController::navigateUp
            )
        }
    }
}