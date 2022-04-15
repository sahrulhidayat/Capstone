package com.sahrulhidayat.capstone.di

import com.sahrulhidayat.capstone.ui.MainViewModel
import com.sahrulhidayat.core.utils.DispatcherProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(DispatcherProvider.main(), get()) }
}