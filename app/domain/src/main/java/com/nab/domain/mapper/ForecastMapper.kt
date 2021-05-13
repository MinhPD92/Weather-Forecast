package com.nab.domain.mapper

import com.nab.domain.CELSIUS_SYMBOL
import com.nab.domain.DATE_FORMAT
import com.nab.domain.DESCRIPTION_SEPARATOR
import com.nab.domain.PERCENT_SYMBOL
import com.nab.domain.models.WeatherInfo
import com.nab.data.remote.response.ForecastResponse
import com.nab.data.remote.response.TemperatureResponse
import java.text.SimpleDateFormat
import java.util.*

fun ForecastResponse.toForecastInfo(): WeatherInfo {
    return WeatherInfo(
        time = (dt * 1000).toTimeFormat(),
        averageTemperature = temp.getAverageTempDisplay(),
        humidity = "$humidity$PERCENT_SYMBOL",
        pressure = "$pressure",
        description = weather.joinToString(separator = DESCRIPTION_SEPARATOR) { weatherResponse ->
            weatherResponse.description
        }
    )
}

fun Long.toTimeFormat(): String{
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    val date = Date(this)
   return simpleDateFormat.format(date)
}

fun TemperatureResponse.getAverageTempDisplay(): String{
    return "${(max + min)/2} $CELSIUS_SYMBOL"
}