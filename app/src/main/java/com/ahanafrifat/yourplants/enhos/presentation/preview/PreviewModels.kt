package com.ahanafrifat.yourplants.enhos.presentation.preview

import com.ahanafrifat.yourplants.enhos.presentation.models.EchoUi
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import com.ahanafrifat.yourplants.enhos.presentation.models.PlaybackState
import java.time.Instant
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

data object PreviewModels {
    val echoUi = EchoUi(
        id = 0,
        title = "My audio name",
        mood = MoodUi.STRESSED,
        recordedAt = Instant.now(),
        note = (1..50).joinToString(" ") { "Hello" },
        topics = listOf("Love", "Work"),
        amplitudes = (1..30).map { Random.nextFloat() },
        playbackTotalDuration = 250.seconds,
        playbackCurrentDuration = 120.seconds,
        playbackState = PlaybackState.PAUSED,
        audioFilePath = ""
    )
}