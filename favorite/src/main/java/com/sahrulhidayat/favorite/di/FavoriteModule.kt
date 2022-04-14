package com.sahrulhidayat.favorite.di

import com.sahrulhidayat.core.utils.DispatcherProvider
import com.sahrulhidayat.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(DispatcherProvider.main(), get()) }
}