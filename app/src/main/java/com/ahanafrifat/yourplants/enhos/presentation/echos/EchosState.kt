package com.ahanafrifat.yourplants.enhos.presentation.echos

import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.Selectable
import com.ahanafrifat.yourplants.core.presentation.designsystem.dropdowns.Selectable.Companion.asUnselectedItems
import com.ahanafrifat.yourplants.core.presentation.util.UiText
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoFilterChip
import com.ahanafrifat.yourplants.enhos.presentation.echos.models.MoodChipContent
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

data class EchosState(
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilter: Boolean = false,
    val hasActiveMoodFilters: Boolean = false,
    val isLoadingData: Boolean = false,
    val moods: List<Selectable<MoodUi>> = emptyList(),
    val topics: List<Selectable<String>> = listOf("Love", "Happy", "Work").asUnselectedItems(),
    val moodChipContent: MoodChipContent = MoodChipContent(),
    val selectedEchoFilterChip: EchoFilterChip? = null,
    val topicChipTitle: UiText = UiText.StringResource(R.string.all_topics)
)
