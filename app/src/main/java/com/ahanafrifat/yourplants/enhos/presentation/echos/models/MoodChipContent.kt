package com.ahanafrifat.yourplants.enhos.presentation.echos.models

import com.ahanafrifat.yourplants.R
import com.ahanafrifat.yourplants.core.presentation.util.UiText

data class MoodChipContent(
    val iconsRes: List<Int> = emptyList(),
    val title: UiText = UiText.StringResource(R.string.all_moods)
)
