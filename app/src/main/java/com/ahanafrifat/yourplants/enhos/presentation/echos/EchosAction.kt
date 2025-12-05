package com.ahanafrifat.yourplants.enhos.presentation.echos

import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoFilterChip
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

sealed interface EchosAction {
    data object OnMoodChipClick : EchosAction
    data object OnDismissMoodDropDown : EchosAction
    data class OnFilterByMoodClick(val moodUi: MoodUi) : EchosAction
    data object OnTopicChipClick : EchosAction
    data object OnDismissTopicDropDown : EchosAction
    data class OnFilterByTopicClick(val topic: String) : EchosAction
    data object OnFabClick : EchosAction
    data object OnFabLongClick : EchosAction
    data object OnSettingClick : EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip) : EchosAction
}