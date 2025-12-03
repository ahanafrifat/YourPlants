package com.ahanafrifat.yourplants.enhos.presentation.echos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class EchosViewModel: ViewModel() {

    private var hasLoadingInitialData = false

    private val _state = MutableStateFlow(EchosState())
    val state = _state
        .onStart {
            if(!hasLoadingInitialData){
                hasLoadingInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = EchosState()
        )

    fun onAction(action: EchosAction){
        when(action){
            EchosAction.OnFabClick -> {

            }
            EchosAction.OnFabLongClick -> {

            }
            EchosAction.OnMoodChipClick -> {

            }
            is EchosAction.OnRemoveFilters -> {

            }
            EchosAction.OnTopicChipClick -> {

            }
            EchosAction.OnSettingClick -> {

            }
        }
    }
}