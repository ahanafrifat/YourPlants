package com.ahanafrifat.yourplants.enhos.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahanafrifat.yourplants.enhos.domain.echo.Mood
import com.ahanafrifat.yourplants.enhos.domain.settings.SettingsPreference
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsPreference: SettingsPreference
) : ViewModel() {
    private var hasLoadedInitialData = false

    private val eventChannel = Channel<SettingsEvent>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(SettingsState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeSettings()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SettingsState()
        )

    fun onAction(action: SettingsAction) {
        when (action) {
            SettingsAction.OnAddButtonClick -> {}
            is SettingsAction.OnSelectTopicClick -> onSelectTopicClick(action.topic)
            SettingsAction.OnDismissTopicDropDown -> {}
            is SettingsAction.OnMoodClick -> onMoodClick(action.mood)
            is SettingsAction.OnRemoveTopicClick -> onRemoveTopicClick(action.topic)
            is SettingsAction.OnSearchTextChange -> {}
            else -> Unit
        }
    }

    private fun onMoodClick(mood: MoodUi) {
        viewModelScope.launch {
            settingsPreference.saveDefaultMood(Mood.valueOf(mood.name))
        }
    }

    private fun onSelectTopicClick(topic: String) {
        viewModelScope.launch {
            val newDefaultTopics = (state.value.topics + topic).distinct()
            settingsPreference.saveDefaultTopics(newDefaultTopics)
        }
    }

    private fun onRemoveTopicClick(topic: String) {
        viewModelScope.launch {
            val newDefaultTopics = (state.value.topics - topic).distinct()
            settingsPreference.saveDefaultTopics(newDefaultTopics)
        }
    }

    private fun observeSettings() {
        combine(
            settingsPreference.observeDefaultTopics(),
            settingsPreference.observeDefaultMood()
        ) { topics, mood ->
            _state.update {
                it.copy(
                    topics = topics,
                    selectedMood = MoodUi.valueOf(mood.name)
                )
            }
        }.launchIn(viewModelScope)
    }

}