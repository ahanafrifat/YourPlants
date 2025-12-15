package com.ahanafrifat.yourplants.enhos.presentation.echos

import com.ahanafrifat.yourplants.enhos.domain.recording.RecordingDetails

interface EchosEvent {
    data object RequestAudioPermission: EchosEvent
    data object RecordingTooShort: EchosEvent
    data class OnDoneRecording(val recordingDetails: RecordingDetails): EchosEvent
}