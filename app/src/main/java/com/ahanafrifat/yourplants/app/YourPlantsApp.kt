package com.ahanafrifat.yourplants.app

import android.app.Application
import com.ahanafrifat.yourplants.BuildConfig
import com.ahanafrifat.yourplants.app.di.appModule
import com.ahanafrifat.yourplants.enhos.di.echoModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class YourPlantsApp : Application() {

    val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@YourPlantsApp)
            modules(
                appModule,
                echoModule
            )
        }
    }
}