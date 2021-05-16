package com.nab.domain.repository

interface LocalForecastRepository {
    suspend fun clearAllWeatherForecastCaches()
}
