package com.ahanafrifat.yourplants.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.ahanafrifat.yourplants.enhos.presentation.create_echo.CreateEchoRoot
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosRoot
import com.ahanafrifat.yourplants.enhos.presentation.settings.SettingsRoot
import com.ahanafrifat.yourplants.enhos.presentation.util.toCreateEchoRoute

const val ACTION_CREATE_ECHO = "com.ahanafrifat.CREATE_ECHO"
@Composable
fun NavigationRoot(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.Echos(
            startRecording = false
        )
    ) {
        composable<NavigationRoute.Echos>(
            deepLinks = listOf(
                navDeepLink<NavigationRoute.Echos> (
                    basePath = "https://ahanafrifat.com/echos"
                ){
                    action = ACTION_CREATE_ECHO
                }
            )
        ) {
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