package com.nab.data.room.repositories

import com.nab.domain.DailyWeatherForecastResult
import com.nab.data.local.LocalForecastService
import com.nab.domain.models.WeatherInfo
import com.nab.domain.repository.LocalForecastRepository
import com.nab.data.room.WeatherForecastDatabase
import com.nab.data.room.mappers.mapToWeatherInfo
import com.nab.data.room.mappers.mapToLocalWeatherInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalWeatherForecastRepositoryImpl @Inject constructor(private val weatherForecastDatabase: WeatherForecastDatabase) :
    LocalForecastRepository, LocalForecastService {
    override suspend fun clearAllWeatherForecastCaches() {
        weatherForecastDatabase.weatherForecastDao().clearAllCache()
    }

    override suspend fun cacheCityWeatherForecast(
        weatherInfoList: List<WeatherInfo>,
        cityName: String
    ) {
        weatherForecastDatabase.weatherForecastDao()
            .cacheCityWeatherForecast(weatherInfoList.map { it.mapToLocalWeatherInfo(cityName = cityName) })
    }

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>> {
        return flow {
            val forecastResponses = weatherForecastDatabase.weatherForecastDao()
                .getWeatherForecastByCityName(cityName = cityName).map {
                it.mapToWeatherInfo()
            }
            if (forecastResponses.isNullOrEmpty()) {
                emit(DailyWeatherForecastResult.NoDataFoundError)
            } else {
                emit(DailyWeatherForecastResult.DailyWeatherForecastSuccess(forecastResponses))
            }
        }
    }
}