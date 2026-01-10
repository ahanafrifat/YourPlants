package com.ahanafrifat.yourplants.enhos.presentation.create_echo

sealed interface CreateEchoEvent {
    data object FailedToSave : CreateEchoEvent
    data object EchoSuccessfullySaved : CreateEchoEvent
}