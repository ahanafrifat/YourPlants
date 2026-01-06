package com.ahanafrifat.yourplants.enhos.data.audio

import android.content.Context
import android.media.MediaPlayer
import com.ahanafrifat.yourplants.enhos.domain.audio.AudioPlayer
import com.ahanafrifat.yourplants.enhos.domain.audio.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AndroidAudioPlayer(
    private val context: Context,
    private val applicationScope: CoroutineScope
) : AudioPlayer {

    private val _activeTrack = MutableStateFlow<AudioTrack?>(null)
    override val activeTrack = _activeTrack.asStateFlow()

    private var mediaPlayer: MediaPlayer? = null

    override fun play(filePath: String, onComplete: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }
}