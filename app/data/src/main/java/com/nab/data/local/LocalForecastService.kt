package com.nab.data.local

import com.nab.data.remote.response.ForecastResponse
import com.nab.data.repositories.ForecastRepository

interface LocalForecastService : ForecastRepository{
    suspend fun cacheCityWeatherForecast(forecastResponses: List<ForecastResponse>, cityName : String)
}