package com.nab.data.deps

import com.nab.data.dataStore.WeatherPreference
import com.nab.data.local.LocalForecastService
import com.nab.domain.repository.RemoteForecastRepository
import com.nab.domain.repository.LocalForecastRepository

interface DataLayerExposeApiProvider {
    fun remoteForecastRepository() : RemoteForecastRepository

    fun localForecastService(): LocalForecastService

    fun localForecastRepository(): LocalForecastRepository

    fun weatherPreferences() : WeatherPreference
}