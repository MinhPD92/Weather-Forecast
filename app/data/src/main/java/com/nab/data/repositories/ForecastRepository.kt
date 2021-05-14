package com.nab.data.repositories

import android.util.Log
import com.nab.data.deps.APP_ID
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
    suspend fun getDailyForecastByCityName(cityName: String): Flow<List<ForecastResponse>>
}

class ForecastRepositoryImpl @Inject constructor(
    private val forecastService: ForecastService,
    private val localService: LocalForecastService,
    @Named(APP_ID)
    private val appId: String
) : ForecastRepository {

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<List<ForecastResponse>> {
        return flow {
            var cacheData: List<ForecastResponse>? = null
            localService.getDailyForecastByCityName(cityName)
                .catch {

                }
                .collect {
                    cacheData = it
                }
            if (cacheData.isNullOrEmpty().not()) {
                Log.d("Minhheo", "getDailyForecastByCityName: cache: ${cacheData}")
                emit(cacheData!!)
            } else {
                val response =
                    forecastService.searchDailyForecastByCityName(key = cityName, appId = appId)
                Log.d("Minhheo", "getDailyForecastByCityName: remote: ${response.response}")

                localService.cacheCityWeatherForecast(
                    forecastResponses = response.response,
                    cityName = cityName
                )
                emit(response.response)
            }
        }
    }

}