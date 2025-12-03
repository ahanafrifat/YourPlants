package com.ahanafrifat.yourplants.enhos.presentation.echos

import com.ahanafrifat.yourplants.enhos.presentation.echos.models.EchoFilterChip

sealed interface EchosAction {
    data object OnMoodChipClick: EchosAction
    data object OnTopicChipClick: EchosAction
    data object OnFabClick: EchosAction
    data object OnFabLongClick: EchosAction
    data object OnSettingClick: EchosAction
    data class OnRemoveFilters(val filterType: EchoFilterChip): EchosAction
}