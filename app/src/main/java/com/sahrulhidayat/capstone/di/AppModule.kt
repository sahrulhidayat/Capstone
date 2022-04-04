package com.sahrulhidayat.capstone.di

import com.sahrulhidayat.core.domain.usecase.GameInteractor
import com.sahrulhidayat.core.domain.usecase.GameUseCase
import com.sahrulhidayat.core.domain.usecase.PreferenceInteractor
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
    factory<PreferenceUseCase> { PreferenceInteractor(get()) }
}