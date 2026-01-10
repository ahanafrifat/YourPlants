package com.ahanafrifat.yourplants.enhos.presentation.util

import com.ahanafrifat.yourplants.enhos.domain.echo.Echo
import com.ahanafrifat.yourplants.enhos.presentation.models.EchoUi
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import com.ahanafrifat.yourplants.enhos.presentation.models.PlaybackState
import kotlin.time.Duration

fun Echo.toEchoUi(
    currentPlaybackDuration: Duration = Duration.ZERO,
    playbackState: PlaybackState = PlaybackState.STOPPED
): EchoUi {
    return EchoUi(
        id = id!!,
        title = title,
        mood = MoodUi.valueOf(mood.name),
        recordedAt = recordedAt,
        note = note,
        topics = topics,
        amplitudes = audioAmplitudes,
        playbackTotalDuration = audioPlaybackLength,
        audioFilePath = audioFilePath,
        playbackCurrentDuration = currentPlaybackDuration,
        playbackState = playbackState
    )
}