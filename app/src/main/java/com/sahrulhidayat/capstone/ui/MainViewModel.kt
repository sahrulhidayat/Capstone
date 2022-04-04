package com.sahrulhidayat.capstone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase

class MainViewModel(private val preferenceUseCase: PreferenceUseCase) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return preferenceUseCase.getThemeSettings().asLiveData()
    }
}