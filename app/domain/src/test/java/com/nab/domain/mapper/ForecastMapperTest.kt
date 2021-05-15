package com.nab.domain.mapper

import com.nab.domain.CELSIUS_SYMBOL
import com.nab.domain.getForecastResponse
import com.nab.domain.getTemperatureResponse
import com.nab.domain.models.WeatherInfo
import org.junit.Test

class ForecastMapperTest {

    @Test
    fun `verify mapper ForeCastResponse to WeatherInfo correctly`(){
        val forecastResponse = getForecastResponse()
        val expectWeatherInfo = WeatherInfo(
            time = "Sat, 15 May 2021",
            averageTemperature = "15.0 $CELSIUS_SYMBOL",
            pressure = "1000",
            humidity = "700%",
            description = "description0, description1"
        )
        val actualWeatherInfo = forecastResponse.toWeatherInfo()

        assert(expectWeatherInfo.time == actualWeatherInfo.time)
        assert(expectWeatherInfo.averageTemperature == actualWeatherInfo.averageTemperature)
        assert(expectWeatherInfo.pressure == actualWeatherInfo.pressure)
        assert(expectWeatherInfo.humidity == actualWeatherInfo.humidity)
        assert(expectWeatherInfo.description == actualWeatherInfo.description)
    }

    @Test
    fun `verify format time in milliseconds to string correctly`(){
        val timeInMillis = 1621068924364L
        val expectTimeFormat = "Sat, 15 May 2021"
        val actualResult = timeInMillis.toTimeFormat()
        assert(actualResult == expectTimeFormat)
    }

    @Test
    fun `verify format average temperature correctly`(){
        val temperatureResponse = getTemperatureResponse()
        val expectFormat = "15.0 $CELSIUS_SYMBOL"
        val actualResult = temperatureResponse.getAverageTempDisplay()
        assert(expectFormat == actualResult)
    }
}