package com.ahanafrifat.yourplants.app.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable
    data class Echos(
        val startRecording: Boolean
    ) : NavigationRoute

    @Serializable
    data class CreateEcho(
        val recordingPath: String,
        val duration: Long,
        val amplitude: String
    ) : NavigationRoute

    @Serializable
    data object Settings: NavigationRoute
}