package com.ahanafrifat.yourplants.enhos.data.recording

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import com.ahanafrifat.yourplants.enhos.domain.recording.RecordingDetails
import com.ahanafrifat.yourplants.enhos.domain.recording.VoiceRecorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.util.UUID

class AndroidVoiceRecording(
    private val context: Context,
    private val applicationScope: CoroutineScope
) : VoiceRecorder {
    companion object {
        private const val TEMP_FILE_PREFIX = "temp_recording"
    }

    private val _recordingDetails = MutableStateFlow(RecordingDetails())
    override val recordingDetails = _recordingDetails.asStateFlow()

    private var tempFile = generateTempFile()
    private var recorder: MediaRecorder? = null
    private var isRecording: Boolean = false
    private val amplitudes = mutableListOf<Float>()
    private var isPaused: Boolean = false

    override fun start() {
        if (isRecording) {
            return
        }

        try {
            resetSession()

            tempFile = generateTempFile()

            recorder = newMediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
                setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
                setAudioEncodingBitRate(128 * 1000)
                setAudioSamplingRate(44100)
                setOutputFile(tempFile.absolutePath)

                prepare()
                start()
            }

            isRecording = true
            isPaused = false

        } catch (e: IOException) {
            Timber.e(e, "Failed to start recording")
            recorder?.release()
            recorder = null
        }
    }

    private fun generateTempFile(): File {
        val id = UUID.randomUUID().toString()
        return File(
            context.cacheDir,
            "${TEMP_FILE_PREFIX}_$id.mp4"
        )
    }

    private fun resetSession() {
        _recordingDetails.update { RecordingDetails() }
        amplitudes.clear()
        cleanup()
    }

    private fun cleanup() {
        Timber.d("Cleaning up voice recorder")
        recorder = null
        isRecording = false
        isPaused = false
    }

    private fun newMediaRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
            MediaRecorder()
        }
    }

    override fun pause() {
        if (!isRecording || isPaused) {
            return
        }
        recorder?.pause()
    }

    override fun stop() {
        try {
            recorder?.apply {
                stop()
                release()
            }
        } catch (e: Exception) {
            Timber.e("Failed to stop recording ${e.message}")
        } finally {
            _recordingDetails.update {
                it.copy(
                    amplitudes = amplitudes.toList(),
                    filePath = tempFile.absolutePath
                )
            }
            cleanup()
        }
    }

    override fun resume() {
        if (!isRecording || isPaused) {
            return
        }
        recorder?.resume()
        isPaused = false
    }

    override fun cancel() {
        stop()
        resetSession()
    }
}