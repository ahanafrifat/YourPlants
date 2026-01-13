package com.ahanafrifat.yourplants.enhos.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class SettingsViewModel: ViewModel() {
    private var hasLoadedInitialData = false
    
    private val eventChannel = Channel<SettingsEvent>()
    val events=eventChannel.receiveAsFlow()
    private val _state= MutableStateFlow(SettingsState())
    val state=_state
        .onStart { 
            if (!hasLoadedInitialData){
                hasLoadedInitialData=true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SettingsState()
        )
    
    fun onAction(action: SettingsAction){
        when(action){
            SettingsAction.OnAddButtonClick -> {}
            SettingsAction.OnBackClick -> {}
            SettingsAction.OnCreateTopicClick -> {}
            SettingsAction.OnDismissTopicDropDown -> {}
            is SettingsAction.OnMoodClick -> {}
            SettingsAction.OnRemoveTopicClick -> {}
            is SettingsAction.OnSearchTextChange -> {}
        }
    }
}