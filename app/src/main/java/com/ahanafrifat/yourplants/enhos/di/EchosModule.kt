package com.ahanafrifat.yourplants.enhos.di

import com.ahanafrifat.yourplants.enhos.data.recording.AndroidVoiceRecorder
import com.ahanafrifat.yourplants.enhos.domain.recording.VoiceRecorder
import com.ahanafrifat.yourplants.enhos.presentation.create_echo.CreateEchoViewModel
import com.ahanafrifat.yourplants.enhos.presentation.echos.EchosViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val echoModule = module {
    single {
        AndroidVoiceRecorder(
            context = androidApplication(),
            applicationScope = get()
        )
    } bind VoiceRecorder::class

    viewModelOf(::EchosViewModel)
    viewModelOf(::CreateEchoViewModel)
}