package com.ahanafrifat.yourplants.enhos.domain.recording

import android.R

interface RecordingStorage {
    suspend fun savePersistently(tempFilePath: R.string): String?
    suspend fun cleanUpTemporaryFiles()

    companion object{
        const val RECORDING_FILE_EXTENSION = "mp4"
    }
}