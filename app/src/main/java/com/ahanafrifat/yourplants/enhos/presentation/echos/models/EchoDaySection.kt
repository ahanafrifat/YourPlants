package com.ahanafrifat.yourplants.enhos.presentation.echos.models

import com.ahanafrifat.yourplants.core.presentation.util.UiText
import com.ahanafrifat.yourplants.enhos.presentation.models.EchoUi

data class EchoDaySection(
    val dateHeader: UiText,
    val echos: List<EchoUi>
)
