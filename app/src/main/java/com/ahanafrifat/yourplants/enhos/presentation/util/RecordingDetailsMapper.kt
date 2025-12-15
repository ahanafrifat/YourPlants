package com.ahanafrifat.yourplants.enhos.presentation.util

import com.ahanafrifat.yourplants.app.navigation.NavigationRoute
import com.ahanafrifat.yourplants.enhos.domain.recording.RecordingDetails
import kotlin.time.Duration.Companion.milliseconds

fun RecordingDetails.toCreateEchoRoute(): NavigationRoute.CreateEcho {
    return NavigationRoute.CreateEcho(
        recordingPath = this.filePath ?: throw IllegalArgumentException(
            "Recording path can't be null!"
        ),
        duration = this.duration.inWholeMilliseconds,
        amplitude = this.amplitudes.joinToString(";")
    )
}

fun NavigationRoute.CreateEcho.toRecordingDetails(): RecordingDetails {
    return RecordingDetails(
        duration = this.duration.milliseconds,
        amplitudes = this.amplitude.split(";").map { it.toFloat() },
        filePath = recordingPath
    )
}