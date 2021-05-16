package com.nab.domain.repository

import com.nab.domain.DailyWeatherForecastResult
import com.nab.domain.models.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface RemoteForecastRepository {
    suspend fun getDailyForecastByCityName(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>>
}