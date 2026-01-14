package com.ahanafrifat.yourplants.enhos.di

import com.ahanafrifat.yourplants.enhos.data.audio.AndroidAudioPlayer
import com.ahanafrifat.yourplants.enhos.data.echo.RoomEchoDataSource
import com.ahanafrifat.yourplants.enhos.data.recording.AndroidVoiceRecorder
import com.ahanafrifat.yourplants.enhos.data.recording.InternalRecordingStorage
import com.ahanafrifat.yourplants.enhos.domain.audio.AudioPlayer
import com.ahanafrifat.yourplants.enhos.domain.echo.EchoDataSource
import com.ahanafrifat.yourplants.enhos.domain.recording.RecordingStorage
import com.ahanafrifat.yourplants.enhos.domain.recording.VoiceRecorder
import com.ahanafrifat.yourplants.enhos.presentation.create_echo.CreateEchoViewModel
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosViewModel
import com.ahanafrifat.yourplants.enhos.presentation.settings.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    singleOf(::AndroidVoiceRecorder) bind VoiceRecorder::class
    singleOf(::InternalRecordingStorage) bind RecordingStorage::class
    singleOf(::AndroidAudioPlayer) bind AudioPlayer::class
    singleOf(::RoomEchoDataSource) bind EchoDataSource::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
    viewModelOf(::SettingsViewModel)
}