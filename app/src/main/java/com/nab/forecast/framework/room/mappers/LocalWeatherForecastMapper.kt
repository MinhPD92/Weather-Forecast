package com.nab.forecast.framework.room.mappers

import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import com.nab.data.remote.response.WeatherResponse
import com.nab.domain.DESCRIPTION_SEPARATOR
import com.nab.forecast.framework.room.LocalWeatherInfo

fun ForecastResponse.mapToLocalWeatherInfo(cityName: String): LocalWeatherInfo {
    return LocalWeatherInfo(cityName = cityName,
        time = dt,
        tempMin = temp.min,
        tempMax = temp.max,
        tempDay = temp.day,
        tempMorning = temp.morn,
        tempEvening = temp.eve,
        tempNight = temp.night,
        humidity = humidity,
        pressure = pressure,
        descriptions = weather.joinToString(separator = DESCRIPTION_SEPARATOR) { weatherResponse ->
            weatherResponse.description
        }
    )
}

fun LocalWeatherInfo.mapToForecastResponse(): ForecastResponse {
    val temperatureResponse = TemperatureResponse(
        min = tempMin, max = tempMax, day = tempDay,
        night = tempNight, eve = tempEvening, morn = tempMorning
    )

    val descriptions: List<WeatherResponse> = descriptions.split(DESCRIPTION_SEPARATOR).map {
        WeatherResponse(description = it.trim())
    }

    return ForecastResponse(
        dt = time,
        temp = temperatureResponse,
        pressure = pressure,
        humidity = humidity,
        weather = descriptions
    )
}