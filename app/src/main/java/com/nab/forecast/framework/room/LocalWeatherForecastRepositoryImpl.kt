package com.nab.forecast.framework.room

import com.nab.data.local.LocalForecastService
import com.nab.data.remote.response.ForecastResponse
import com.nab.data.repositories.LocalForecastRepository
import com.nab.forecast.framework.room.mappers.mapToForecastResponse
import com.nab.forecast.framework.room.mappers.mapToLocalWeatherInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalWeatherForecastRepositoryImpl @Inject constructor(private val weatherForecastDatabase: WeatherForecastDatabase) : LocalForecastRepository, LocalForecastService {
    override suspend fun clearAllWeatherForecastCaches() {
        weatherForecastDatabase.weatherForecastDao().clearAllCache()
    }

    override suspend fun cacheCityWeatherForecast(forecastResponses: List<ForecastResponse>, cityName: String) {
        weatherForecastDatabase.weatherForecastDao().cacheCityWeatherForecast(forecastResponses.map { it.mapToLocalWeatherInfo(cityName = cityName) })
    }

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<List<ForecastResponse>> {
       return flow {
            val forecastResponses = weatherForecastDatabase.weatherForecastDao().getWeatherForecastByCityName(cityName = cityName).map {
                it.mapToForecastResponse()
            }
            emit(forecastResponses)
        }
    }
}