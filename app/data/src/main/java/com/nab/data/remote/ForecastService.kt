package com.nab.data.remote

import com.nab.data.remote.response.DailyForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {

    @GET("data/2.5/forecast/daily")
    suspend fun searchDailyForecastByCityName(@Query("q") key: String,
                                              @Query("cnt") period: Int = 7,
                                              @Query("appId") appId : String
    ) : DailyForecastResponse
}