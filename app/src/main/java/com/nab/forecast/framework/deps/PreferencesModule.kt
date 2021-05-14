package com.nab.forecast.framework.deps

import android.content.Context
import com.nab.forecast.framework.dataStore.WeatherForecastPreferenceImpl
import com.nab.forecast.framework.dataStore.WeatherPreference
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