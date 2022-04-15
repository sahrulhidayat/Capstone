package com.sahrulhidayat.settings.di

import com.sahrulhidayat.core.utils.DispatcherProvider
import com.sahrulhidayat.settings.ui.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    viewModel { SettingsViewModel(DispatcherProvider.main(), get()) }
}