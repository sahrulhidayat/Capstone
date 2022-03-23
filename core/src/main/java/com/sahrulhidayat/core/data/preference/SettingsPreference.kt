package com.sahrulhidayat.core.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sahrulhidayat.core.domain.`interface`.ISettingsPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsPreference constructor(
    private val dataStore: DataStore<Preferences>
    ) : ISettingsPreference {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    override fun getThemeSettings(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkMode
        }
    }
}