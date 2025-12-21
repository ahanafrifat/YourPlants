package com.ahanafrifat.yourplants.app.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahanafrifat.yourplants.enhos.presentation.create_echo.CreateEchoRoot
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosRoot
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
                }
            )
        }
        composable<NavigationRoute.CreateEcho> {
            CreateEchoRoot()
        }
    }
}