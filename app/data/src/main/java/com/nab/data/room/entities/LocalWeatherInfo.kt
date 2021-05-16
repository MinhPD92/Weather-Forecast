package com.nab.data.room.entities

import androidx.room.Entity

@Entity(tableName = "weatherForecast", primaryKeys = ["cityName", "time"])
data class LocalWeatherInfo(
    val cityName: String,
    val time: String,
    val averageTemp: String,
    val pressure: String,
    val humidity: String,
    val descriptions: String
)

