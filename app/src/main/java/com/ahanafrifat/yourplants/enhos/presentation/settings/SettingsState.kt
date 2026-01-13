package com.ahanafrifat.yourplants.enhos.presentation.settings

import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

data class SettingsState(
    val topics: List<String> = emptyList(),
    val selectedMood: MoodUi? = null,
    val searchText: String = "",
    val suggestedTopics: List<String> = emptyList(),
    val isTopicSuggestionVisible: Boolean = false,
    val showCreateTopicOption: Boolean = false,
    val isTopicTextInputVisible: Boolean = false
)
