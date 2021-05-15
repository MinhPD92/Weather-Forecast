package com.nab.domain

import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import com.nab.data.remote.response.WeatherResponse

fun getTemperatureResponse(): TemperatureResponse {
    return TemperatureResponse(
        min = 10F,
        max = 20F
    )
}

fun getWeatherResponse(): List<WeatherResponse> {
    return mutableListOf<WeatherResponse>().apply {
        repeat(2) {
            add(WeatherResponse(description = "description$it"))
        }
    }
}

fun getForecastResponse(): ForecastResponse {
    return ForecastResponse(
        dt = 1621068924L,
        temp = getTemperatureResponse(),
        pressure = 1000,
        humidity = 700,
        weather = getWeatherResponse()
    )
}