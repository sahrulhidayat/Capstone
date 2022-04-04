@file:Suppress("unused")

package com.sahrulhidayat.capstone

import android.app.Application
import com.sahrulhidayat.capstone.di.appModule
import com.sahrulhidayat.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    dataStoreModule,
                    useCaseModule,
                    appModule
                )
            )
        }
    }
}