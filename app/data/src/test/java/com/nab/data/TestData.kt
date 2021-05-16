package com.nab.data

import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import com.nab.data.remote.response.WeatherResponse
import com.nab.data.room.entities.LocalWeatherInfo
import com.nab.domain.models.WeatherInfo

const val TIME = "Tue, 10 Mar 2021"
const val PRESSURE = "1000"
const val HUMIDITY = "70%"
const val DESCRIPTION = "description0, description1"
const val AVERAGE_TEMP = "30 C"

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

fun getWeatherInfoList(): List<WeatherInfo> {
    return listOf(
        getWeatherInfo()
    )
}

fun getWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        time = TIME,
        pressure = PRESSURE,
        humidity = HUMIDITY,
        averageTemperature = AVERAGE_TEMP,
        description = DESCRIPTION
    )
}

fun getLocalWeatherInfo(cityName: String): LocalWeatherInfo {
    return LocalWeatherInfo(
        cityName = cityName,
        time = TIME,
        pressure = PRESSURE,
        humidity = HUMIDITY,
        descriptions = DESCRIPTION,
        averageTemp = AVERAGE_TEMP
    )
}