package com.nab.data.deps

import android.content.Context
import com.nab.data.dataStore.WeatherForecastPreferenceImpl
import com.nab.data.dataStore.WeatherPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    fun providerPreferences(context: Context) : WeatherPreference {
        return WeatherForecastPreferenceImpl(context)
    }
}