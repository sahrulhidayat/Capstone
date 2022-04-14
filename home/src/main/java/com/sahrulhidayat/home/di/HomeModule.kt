package com.sahrulhidayat.home.di

import com.sahrulhidayat.home.ui.HomeViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(provideMainDispatcher(), get()) }
}

private fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main