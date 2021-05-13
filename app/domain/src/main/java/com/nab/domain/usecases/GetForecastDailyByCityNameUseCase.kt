package com.nab.domain.usecases

import com.nab.domain.mapper.toForecastInfo
import com.nab.domain.models.WeatherInfo
import com.nab.data.repositories.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetForecastDailyByCityNameUseCase {
    suspend fun getDailyForecast(cityName: String): Flow<List<WeatherInfo>>
}

class GetForecastDailyByCityNameUseCaseImpl @Inject constructor(private val forecastRepository: ForecastRepository) :
    GetForecastDailyByCityNameUseCase {

    override suspend fun getDailyForecast(cityName: String): Flow<List<WeatherInfo>> {
        return forecastRepository.getDailyForecastByCityName(cityName = cityName).map {
            it.map { forecastResponse -> forecastResponse.toForecastInfo() }
        }
    }

}