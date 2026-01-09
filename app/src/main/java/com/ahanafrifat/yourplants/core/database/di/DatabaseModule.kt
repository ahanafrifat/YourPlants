package com.ahanafrifat.yourplants.core.database.di

import androidx.room.Room
import com.ahanafrifat.yourplants.core.database.EchoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<EchoDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            EchoDatabase::class.java,
            "echos.db",
        ).build()
    }
    single {
        get<EchoDatabase>().echoDao
    }
}