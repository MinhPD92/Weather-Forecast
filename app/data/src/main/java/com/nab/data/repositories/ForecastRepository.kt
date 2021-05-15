package com.nab.data.repositories

import com.nab.configurations.deps.APP_ID
import com.nab.data.DailyWeatherForecastResult
import com.nab.data.extensions.runNetworkSafety
import com.nab.data.local.LocalForecastService
import com.nab.data.remote.ForecastService
import com.nab.data.remote.response.ForecastResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

interface ForecastRepository {
    suspend fun getDailyForecastByCityName(cityName: String): Flow<DailyWeatherForecastResult<List<ForecastResponse>>>
}

class ForecastRepositoryImpl @Inject constructor(
    private val forecastService: ForecastService,
    private val localService: LocalForecastService,
    @Named(APP_ID)
    private val appId: String
) : ForecastRepository {

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<DailyWeatherForecastResult<List<ForecastResponse>>> {
        return flow {
            var cacheData: DailyWeatherForecastResult<List<ForecastResponse>>? = null
            localService.getDailyForecastByCityName(cityName)
                .catch {
                    // catch to make sure any error from local will not interrupt flow
                }
                .collect {
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

    private suspend fun getDailyWeatherForecastByCityName(cityName: String): DailyWeatherForecastResult<List<ForecastResponse>> {
        return runNetworkSafety {
            val response =
                forecastService.searchDailyForecastByCityName(key = cityName, appId = appId)
            // cache response
            cacheResponse(response = response.response, cityName = cityName)
            DailyWeatherForecastResult.DailyWeatherForecastSuccess(response.response)
        }
    }

    private suspend fun cacheResponse(response: List<ForecastResponse>, cityName: String) {
        localService.cacheCityWeatherForecast(
            forecastResponses = response,
            cityName = cityName
        )
    }
}