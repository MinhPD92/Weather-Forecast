package com.nab.data.repositories

import com.nab.data.deps.APP_ID
import com.nab.data.remote.ForecastService
import com.nab.data.remote.response.ForecastResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

interface ForecastRepository {
    suspend fun getDailyForecastByCityName(cityName: String): Flow<List<ForecastResponse>>
}

class ForecastRepositoryImpl @Inject constructor(
    private val forecastService: ForecastService,
    @Named(APP_ID)
    private val appId: String
) : ForecastRepository {

    override suspend fun getDailyForecastByCityName(cityName: String): Flow<List<ForecastResponse>> {
        return flow {
            val response = forecastService.searchDailyForecastByCityName(key = cityName, appId = appId)
            emit(response.response)
        }
    }

}