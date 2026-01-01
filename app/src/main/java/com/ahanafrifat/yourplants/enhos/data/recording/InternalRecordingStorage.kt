package com.ahanafrifat.yourplants.enhos.data.recording

import android.content.Context
import com.ahanafrifat.yourplants.enhos.domain.recording.RecordingStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.time.Instant
import java.time.temporal.ChronoUnit

class InternalRecordingStorage(
    private val context: Context
) : RecordingStorage {
    override suspend fun savePersistently(tempFilePath: String): String? {
        val tempFile = File(tempFilePath)
        if (!tempFile.exists()) {
            Timber.e("The temporary file does not exist.")
            return null
        }

        return withContext(Dispatchers.IO) {
            try {
                val saveFile = generateSavedFile()
                tempFile.copyTo(saveFile)
                saveFile.absolutePath
            } catch (e: IOException) {
                Timber.e(e)
                null
            } finally {
                cleanUpTemporaryFiles()
            }
        }
    }

    override suspend fun cleanUpTemporaryFiles() {
        withContext(Dispatchers.IO) {
            context
                .cacheDir
                .listFiles()
                ?.filter { it.name.startsWith(RecordingStorage.TEMP_FILE_PREFIX) }
                ?.forEach { file ->
                    file.delete()
                }
        }
    }

    private fun generateSavedFile(): File {
        val timestamp = Instant.now().truncatedTo(ChronoUnit.SECONDS).toString()
        return File(
            context.filesDir,
            "${RecordingStorage.PERSISTENT_FILE_PREFIX}_$timestamp.${RecordingStorage.RECORDING_FILE_EXTENSION}"
        )
    }
}