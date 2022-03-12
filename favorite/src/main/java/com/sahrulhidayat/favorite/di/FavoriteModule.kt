package com.sahrulhidayat.favorite.di

import com.sahrulhidayat.favorite.ui.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}