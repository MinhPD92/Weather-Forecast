package com.nab.domain.usecases

import com.nab.data.DailyWeatherForecastResult
import com.nab.domain.mapper.toForecastInfo
import com.nab.domain.models.WeatherInfo
import com.nab.data.repositories.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetForecastDailyByCityNameUseCase {
    suspend fun getDailyForecast(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>>
}

class GetForecastDailyByCityNameUseCaseImpl @Inject constructor(private val forecastRepository: ForecastRepository) :
    GetForecastDailyByCityNameUseCase {

    override suspend fun getDailyForecast(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>> {
        return forecastRepository.getDailyForecastByCityName(cityName = cityName).map {
            when (it) {
                is DailyWeatherForecastResult.DailyWeatherForecastSuccess -> {
                    DailyWeatherForecastResult.DailyWeatherForecastSuccess(it.repsonse.map { forecastResponse -> forecastResponse.toForecastInfo() })
                }
                else -> {
                    it as DailyWeatherForecastResult.DailyWeatherForecastError
                }
            }
        }

    }

}