package com.nab.forecast.framework.room

import androidx.room.Entity

@Entity(tableName = "weatherForecast", primaryKeys = ["cityName", "time"])
data class LocalWeatherInfo(
    val cityName: String,
    val time: Long,
    val tempMin: Float,
    val tempMax: Float,
    val tempDay: Float,
    val tempNight: Float,
    val tempEvening: Float,
    val tempMorning: Float,
    val pressure: Int,
    val humidity: Int,
    val descriptions: String
)

