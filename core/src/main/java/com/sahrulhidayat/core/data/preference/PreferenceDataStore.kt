package com.sahrulhidayat.core.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.sahrulhidayat.core.data.preference.PreferenceDataStore.PreferencesKeys.THEME_KEY
import com.sahrulhidayat.core.domain.interfaces.IPreferenceDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceDataStore (val context: Context) : IPreferenceDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private object PreferencesKeys {
        val THEME_KEY = booleanPreferencesKey("theme_setting")
    }

    override fun getThemeSettings(): Flow<Boolean> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkMode
        }
    }
}