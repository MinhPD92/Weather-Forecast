package com.nab.forecast.framework.deps

import com.nab.data.local.LocalForecastService
import com.nab.data.repositories.LocalForecastRepository
import com.nab.forecast.framework.dataStore.WeatherPreference

interface FrameworkComponentApiExpose {

    fun localForecastService(): LocalForecastService

    fun localForecastRepository(): LocalForecastRepository

    fun weatherPreferences() : WeatherPreference
}