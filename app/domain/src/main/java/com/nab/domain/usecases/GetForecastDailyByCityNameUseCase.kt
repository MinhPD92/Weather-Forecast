package com.nab.domain.usecases

import com.nab.domain.DailyWeatherForecastResult
import com.nab.domain.models.WeatherInfo
import com.nab.domain.repository.RemoteForecastRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetForecastDailyByCityNameUseCase {
    suspend fun getDailyForecast(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>>
}

class GetForecastDailyByCityNameUseCaseImpl @Inject constructor(private val forecastRepository: RemoteForecastRepository) :
    GetForecastDailyByCityNameUseCase {

    override suspend fun getDailyForecast(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>> {
        return forecastRepository.getDailyForecastByCityName(cityName = cityName)
    }

}