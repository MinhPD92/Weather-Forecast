package com.nab.data.repositories

import com.nab.configurations.deps.APP_ID
import com.nab.domain.DailyWeatherForecastResult
import com.nab.data.extensions.runNetworkSafety
import com.nab.data.local.LocalForecastService
import com.nab.data.mapper.toWeatherInfo
import com.nab.data.remote.ForecastService
import com.nab.domain.models.WeatherInfo
import com.nab.domain.repository.RemoteForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class RemoteForecastRepositoryImpl @Inject constructor(
    private val forecastService: ForecastService,
    private val localService: LocalForecastService,
    @Named(APP_ID)
    private val appId: String
) : RemoteForecastRepository {

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<DailyWeatherForecastResult<List<WeatherInfo>>> {
        return flow {
            var cacheData: DailyWeatherForecastResult<List<WeatherInfo>>? = null
            localService.getDailyForecastByCityName(cityName)
                .catch {
                    // catch to make sure any error from local will not interrupt flow
                }.collect {
                    cacheData = it
                }
            if (cacheData is DailyWeatherForecastResult.DailyWeatherForecastSuccess) {
                emit(cacheData!!)
            } else {
                val result = getDailyWeatherForecastByCityName(cityName = cityName)
                emit(result)
            }
        }
    }

    private suspend fun getDailyWeatherForecastByCityName(cityName: String): DailyWeatherForecastResult<List<WeatherInfo>> {
        return runNetworkSafety {
            val response =
                forecastService.searchDailyForecastByCityName(key = cityName, appId = appId)
            val weatherInfoListResponse = response.response.map { it.toWeatherInfo() }
            // cache response
            cacheResponse(response = weatherInfoListResponse, cityName = cityName)
            DailyWeatherForecastResult.DailyWeatherForecastSuccess(weatherInfoListResponse)
        }
    }

    private suspend fun cacheResponse(response: List<WeatherInfo>, cityName: String) {
        localService.cacheCityWeatherForecast(
            weatherInfoList = response,
            cityName = cityName
        )
    }
}