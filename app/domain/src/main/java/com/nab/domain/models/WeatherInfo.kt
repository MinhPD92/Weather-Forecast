package com.nab.domain.models

data class WeatherInfo(
    val time: String,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)
