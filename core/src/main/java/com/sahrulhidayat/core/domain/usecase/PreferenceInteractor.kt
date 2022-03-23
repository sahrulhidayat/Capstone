package com.sahrulhidayat.core.domain.usecase

import com.sahrulhidayat.core.domain.`interface`.ISettingsPreference
import kotlinx.coroutines.flow.Flow

class PreferenceInteractor(private val settingsPreference: ISettingsPreference): PreferenceUseCase {
    override fun getThemeSettings(): Flow<Boolean> {
        return settingsPreference.getThemeSettings()
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        return settingsPreference.saveThemeSettings(isDarkMode)
    }
}