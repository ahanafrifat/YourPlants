package com.ahanafrifat.yourplants.app.di

import com.ahanafrifat.yourplants.app.YourPlantsApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as YourPlantsApp).applicationScope
    }
}