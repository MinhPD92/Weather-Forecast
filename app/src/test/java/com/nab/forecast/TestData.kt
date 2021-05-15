package com.nab.forecast

import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import com.nab.data.remote.response.WeatherResponse
import com.nab.domain.CELSIUS_SYMBOL
import com.nab.domain.DESCRIPTION_SEPARATOR
import com.nab.domain.PERCENT_SYMBOL
import com.nab.domain.models.WeatherInfo
import com.nab.forecast.framework.room.model.LocalWeatherInfo

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

fun getLocalWeatherInfo(cityName: String): LocalWeatherInfo {
    return LocalWeatherInfo(
        cityName = cityName,
        time = TIME,
        tempMin = MIN_TEMP,
        tempMax = MAX_TEMP,
        tempDay = 0F,
        tempNight = 0F,
        tempEvening = 0F,
        tempMorning = 0F,
        pressure = PRESSURE,
        humidity = HUMIDITY,
        descriptions = "${DESCRIPTION}0$DESCRIPTION_SEPARATOR${DESCRIPTION}1"
    )
}