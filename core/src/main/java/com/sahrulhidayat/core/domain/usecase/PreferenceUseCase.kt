package com.sahrulhidayat.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface PreferenceUseCase {

    fun getThemeSettings(): Flow<Boolean>

    suspend fun saveThemeSettings(isDarkMode: Boolean)

}