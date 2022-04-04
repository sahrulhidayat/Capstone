package com.sahrulhidayat.settings.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sahrulhidayat.core.domain.usecase.PreferenceUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferenceUseCase: PreferenceUseCase) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return preferenceUseCase.getThemeSettings().asLiveData()
    }

    fun setThemeSettings(isDarkMode: Boolean) {
        viewModelScope.launch {
            preferenceUseCase.saveThemeSettings(isDarkMode)
        }
    }
}