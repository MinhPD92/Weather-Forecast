package com.nab.data.local

import com.nab.domain.models.WeatherInfo
import com.nab.domain.repository.RemoteForecastRepository

interface LocalForecastService : RemoteForecastRepository {
    suspend fun cacheCityWeatherForecast(weatherInfoList: List<WeatherInfo>, cityName : String)
}