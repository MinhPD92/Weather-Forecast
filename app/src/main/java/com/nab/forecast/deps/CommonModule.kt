package com.nab.forecast.deps

import android.content.Context
import android.content.SharedPreferences
import com.nab.forecast.dispatcherProvider.DefaultDispatcherProvider
import com.nab.forecast.dispatcherProvider.DispatcherProvider
import com.nab.forecast.framework.dataStore.WeatherForecastPreferenceImpl
import com.nab.forecast.framework.dataStore.WeatherPreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun providerDispatcherProvider(): DispatcherProvider{
        return DefaultDispatcherProvider()
    }
}