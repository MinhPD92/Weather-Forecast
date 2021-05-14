package com.nab.data.repositories

interface LocalForecastRepository {
    suspend fun clearAllWeatherForecastCaches()
}
