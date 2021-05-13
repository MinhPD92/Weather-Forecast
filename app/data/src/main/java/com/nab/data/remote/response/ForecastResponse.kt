package com.nab.data.remote.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("dt") val dt: Long,
    @SerializedName("temp") val temp: TemperatureResponse,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: List<WeatherResponse>
)