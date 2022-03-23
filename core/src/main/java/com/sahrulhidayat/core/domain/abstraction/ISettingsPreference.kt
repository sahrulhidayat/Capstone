package com.sahrulhidayat.core.domain.abstraction

import kotlinx.coroutines.flow.Flow

interface ISettingsPreference {

    fun getThemeSettings(): Flow<Boolean>

    suspend fun saveThemeSettings(isDarkMode: Boolean)

}