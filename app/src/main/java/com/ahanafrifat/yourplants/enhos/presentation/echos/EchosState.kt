package com.ahanafrifat.yourplants.enhos.presentation.echos

data class EchosState(
    val hasEchosRecorded: Boolean = false,
    val hasActiveTopicFilter: Boolean = false,
    val hasActiveMoodFilters:Boolean = false,
    val isLoadingData: Boolean = false
)
