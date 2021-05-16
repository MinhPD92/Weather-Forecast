package com.nab.domain

import com.nab.domain.models.WeatherInfo

const val TIME = "Tue, 10 Mar 2021"
const val PRESSURE = "1000"
const val HUMIDITY = "70%"
const val DESCRIPTION = "description0, description1"
const val AVERAGE_TEMP = "30 C"

fun getWeatherInfo(): WeatherInfo {
    return WeatherInfo(
        time = TIME,
        pressure = PRESSURE,
        humidity = HUMIDITY,
        averageTemperature = AVERAGE_TEMP,
        description = DESCRIPTION
    )
}

fun getWeatherInfoList(): List<WeatherInfo> {
    return listOf(
        getWeatherInfo()
    )
}