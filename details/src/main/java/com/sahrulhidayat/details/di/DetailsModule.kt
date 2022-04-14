package com.sahrulhidayat.details.di

import com.sahrulhidayat.core.utils.DispatcherProvider
import com.sahrulhidayat.details.ui.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    viewModel { DetailsViewModel(DispatcherProvider.main(), get()) }
}