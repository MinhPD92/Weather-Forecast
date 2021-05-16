package com.nab.forecast

import com.nab.data.constants.CELSIUS_SYMBOL
import com.nab.data.constants.PERCENT_SYMBOL
import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import com.nab.data.remote.response.WeatherResponse
import com.nab.domain.models.WeatherInfo

const val PRESSURE = 700
const val AVERAGE_TEMP = 20
const val MAX_TEMP = 30F
const val MIN_TEMP = 10F
const val HUMIDITY = 30
const val DESCRIPTION = "description"
const val DAYS = 7
const val TIME = 12345L

internal fun getWeatherInfoList(): List<WeatherInfo> {
    return mutableListOf<WeatherInfo>().apply {
        repeat(DAYS) {
            add(
                WeatherInfo(
                    time = TIME.toString(),
                    averageTemperature = "$AVERAGE_TEMP$CELSIUS_SYMBOL",
                    pressure = PRESSURE.toString(),
                    humidity = "$HUMIDITY$PERCENT_SYMBOL",
                    description = DESCRIPTION
                )
            )
        }
    }
}

fun getTemperatureResponse(): TemperatureResponse {
    return TemperatureResponse(
        min = MIN_TEMP,
        max = MAX_TEMP
    )
}

fun getWeatherResponse(): List<WeatherResponse> {
    return mutableListOf<WeatherResponse>().apply {
        repeat(2) {
            add(WeatherResponse(description = "$DESCRIPTION$it"))
        }
    }
}

fun getForecastResponse(): ForecastResponse {
    return ForecastResponse(
        dt = 12345L,
        temp = getTemperatureResponse(),
        pressure = PRESSURE,
        humidity = HUMIDITY,
        weather = getWeatherResponse()
    )
}