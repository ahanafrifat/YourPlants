package com.ahanafrifat.yourplants.enhos.presentation.settings

import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

sealed interface SettingsAction {
    data class OnSearchTextChange(val text: String) : SettingsAction
    data object OnCreateTopicClick : SettingsAction
    data object OnRemoveTopicClick : SettingsAction
    data object OnBackClick : SettingsAction
    data object OnDismissTopicDropDown : SettingsAction
    data object OnAddButtonClick : SettingsAction
    data class OnMoodClick(val mood: MoodUi): SettingsAction
}