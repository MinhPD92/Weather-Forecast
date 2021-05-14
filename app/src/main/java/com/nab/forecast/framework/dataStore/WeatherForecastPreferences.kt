package com.nab.forecast.framework.dataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.*
import javax.inject.Inject

const val WEATHER_FORECAST_PREFERENCE = "WEATHER_FORECAST_PREFERENCE"

interface WeatherPreference {
    suspend fun isOnNewDay(time: Long): Flow<Boolean>
}

class WeatherForecastPreferenceImpl @Inject constructor(context: Context) : WeatherPreference {

    private val checkingTimeKey = longPreferencesKey("CHECKING_TIME_KEY")

    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create {
        context.applicationContext.preferencesDataStoreFile(WEATHER_FORECAST_PREFERENCE)
    }

    override suspend fun isOnNewDay(time: Long): Flow<Boolean> {
        saveNewCheckingTime(time)
        return dataStore.data.map {
            val previousCheckingTime = it[checkingTimeKey] ?: 0L
            previousCheckingTime < time
        }
    }

    private suspend fun saveNewCheckingTime(time: Long) {
        dataStore.edit { preferences ->
            preferences[checkingTimeKey] = time
        }
    }

}