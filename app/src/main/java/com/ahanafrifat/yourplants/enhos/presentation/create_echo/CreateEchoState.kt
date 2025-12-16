package com.ahanafrifat.yourplants.enhos.presentation.create_echo

import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import com.ahanafrifat.yourplants.enhos.presentation.models.PlaybackState
import kotlin.time.Duration

data class CreateEchoState(
    val titleText: String = "",
    val addTopicText: String = "",
    val noteText: String = "",
    val showMoodSelector: Boolean = true,
    val selectedMood: MoodUi = MoodUi.NEUTRAL,
    val showTopicSuggestions: Boolean = false,
    val moodUi: MoodUi? = null,
    val searchResults: List<String> = emptyList(),
    val showCreateTopicOption: Boolean = false,
    val canSaveEcho: Boolean = false,
    val playbackAmplitudes: List<Float> = emptyList(),
    val playbackTotalDuration: Duration = Duration.ZERO,
    val playbackState: PlaybackState = PlaybackState.STOPPED,
    val durationPlayed: Duration = Duration.ZERO
) {
    val durationPlayedRatio = (durationPlayed / playbackTotalDuration).toFloat()
}
