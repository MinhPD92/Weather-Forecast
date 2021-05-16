package com.nab.data.deps

import android.content.Context
import com.nab.data.room.WeatherForecastDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherForecastDatabase(context: Context) : WeatherForecastDatabase{
        return WeatherForecastDatabase.buildDatabase(context)
    }
}