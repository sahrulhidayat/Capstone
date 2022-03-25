package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.domain.interfaces.IPreferenceDataStore
import kotlinx.coroutines.flow.Flow

class PreferenceInteractor(
    private val preferenceDataStore: IPreferenceDataStore
) : PreferenceUseCase {
    override fun getThemeSettings(): Flow<Boolean> {
        return preferenceDataStore.getThemeSettings()
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        return preferenceDataStore.saveThemeSettings(isDarkMode)
    }
}