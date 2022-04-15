package com.sahrulhidayat.capstone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class MainViewModel(
    private val mainDispatcher: CoroutineDispatcher,
    private val preferenceUseCase: PreferenceUseCase
) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> = getThemeSettingsFlow().asLiveData()

    fun getThemeSettingsFlow(): Flow<Boolean> {
        return preferenceUseCase.getThemeSettings().flowOn(mainDispatcher)
    }
}