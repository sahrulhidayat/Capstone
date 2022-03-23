package com.sahrulhidayat.capstone.di

import com.sahrulhidayat.capstone.ui.detail.DetailsViewModel
import com.sahrulhidayat.capstone.ui.home.HomeViewModel
import com.sahrulhidayat.capstone.ui.settings.SettingsViewModel
import com.sahrulhidayat.core.domain.usecase.GameInteractor
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import com.sahrulhidayat.core.domain.usecase.PreferenceInteractor
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
    factory<PreferenceUseCase> { PreferenceInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}