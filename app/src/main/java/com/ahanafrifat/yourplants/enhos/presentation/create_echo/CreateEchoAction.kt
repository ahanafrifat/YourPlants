package com.ahanafrifat.yourplants.enhos.presentation.create_echo

import com.ahanafrifat.yourplants.enhos.presentation.echos.models.TrackSizeInfo
import com.ahanafrifat.yourplants.enhos.presentation.models.MoodUi

sealed interface CreateEchoAction {
    data object OnNavigateBackClick : CreateEchoAction
    data class OnTitleChange(val text: String) : CreateEchoAction
    data class OnAddTopicTextChange(val text: String) : CreateEchoAction
    data class OnNoteTextChange(val text: String) : CreateEchoAction
    data object ShowMoodSelector : CreateEchoAction
    data object OnDismissMoodSelector : CreateEchoAction
    data class OnMoodClick(val moodUi: MoodUi) : CreateEchoAction
    data object OnConfirmMood : CreateEchoAction
    data class OnTopicClick(val topic: String) : CreateEchoAction
    data object OnDismissTopicSuggestions : CreateEchoAction
    data object OnCancelClick : CreateEchoAction
    data object OnSaveClick : CreateEchoAction
    data object OnCreateNewTopicClick : CreateEchoAction
    data object OnPlayAudioClick : CreateEchoAction
    data object OnPauseAudioClick : CreateEchoAction
    data class OnTrackSizeAvailable(val trackSizeInfo: TrackSizeInfo) : CreateEchoAction
}