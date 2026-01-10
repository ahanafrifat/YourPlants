package com.ahanafrifat.yourplants.enhos.presentation.echos

import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.Selectable
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.Selectable.Companion.asUnselectedItems
import com.ahanafrifat.yourplants.core.presentation.util.UiText
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.AudioCaptureMethod
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoDaySection
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoFilterChip
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.MoodChipContent
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.RecordingState
import com.ahanafrifat.yourplants.enhos.presentation.models.EchoUi
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import java.util.Locale
import kotlin.math.roundToInt
import kotlin.time.Duration

data class EchosState(
    val echos: Map<UiText, List<EchoUi>> = emptyMap(),
    val currentCaptureMethod: AudioCaptureMethod? = null,
    val recordingElapseDuration: Duration = Duration.ZERO,
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilters: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = true,
    val recordingState: RecordingState = RecordingState.NOT_RECORDING,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = emptyList(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics)
) {
    val echoDaySection = echos
        .toList()
        .map { (dateHeader, echos) ->
            EchoDaySection(dateHeader, echos)
        }

    val formattedRecordDuration: String
        get() {
            val minutes = (recordingElapseDuration.inWholeMinutes % 60).toInt()
            val seconds = (recordingElapseDuration.inWholeSeconds % 60).toInt()
            val centiseconds =
                ((recordingElapseDuration.inWholeMilliseconds % 1000) / 10.0).roundToInt()

            return String.format(
                locale = Locale.US,
                format = "%02d:%02d:%02d",
                minutes, seconds, centiseconds
            )
        }
}
