package com.ahanafrifat.yourplants.enhos.domain.settings

import com.ahanafrifat.yourplants.enhos.domain.echo.Mood
import kotlinx.coroutines.flow.Flow

interface SettingsPreference {
    suspend fun saveDefaultTopics(topics: List<String>)
    fun observeDefaultTopics(): Flow<List<String>>

    suspend fun saveDefaultMood(mood: Mood)
    fun observeDefaultMood(): Flow<Mood>
}