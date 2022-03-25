package com.sahrulhidayat.capstone

import android.app.Application
import com.sahrulhidayat.capstone.di.useCaseModule
import com.sahrulhidayat.capstone.di.viewModelModule
import com.sahrulhidayat.core.di.dataStoreModule
import com.sahrulhidayat.core.di.databaseModule
import com.sahrulhidayat.core.di.networkModule
import com.sahrulhidayat.core.di.repositoryModule
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
                    viewModelModule
                )
            )
        }
    }
}