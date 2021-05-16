package com.nab.data.room.mappers

import com.nab.domain.models.WeatherInfo
import com.nab.data.room.entities.LocalWeatherInfo

fun WeatherInfo.mapToLocalWeatherInfo(cityName: String): LocalWeatherInfo {
    return LocalWeatherInfo(
        cityName = cityName,
        time = time,
        averageTemp = averageTemperature,
        humidity = humidity,
        pressure = pressure,
        descriptions = description
    )
}

fun LocalWeatherInfo.mapToWeatherInfo(): WeatherInfo {

    return WeatherInfo(
        time = time,
        averageTemperature = averageTemp,
        pressure = pressure,
        humidity = humidity,
        description = descriptions
    )
}