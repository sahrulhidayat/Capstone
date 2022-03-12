package com.sahrulhidayat.capstone.di

import com.sahrulhidayat.capstone.detail.DetailsViewModel
import com.sahrulhidayat.capstone.home.HomeViewModel
import com.sahrulhidayat.capstone.search.SearchViewModel
import com.sahrulhidayat.core.domain.usecase.GameInteractor
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}