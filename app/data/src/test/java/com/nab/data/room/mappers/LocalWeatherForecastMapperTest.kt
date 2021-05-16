package com.nab.data.room.mappers

import com.nab.data.getLocalWeatherInfo
import com.nab.data.getWeatherInfo
import org.junit.Test

class LocalWeatherForecastMapperTest {

    @Test
    fun `verify WeatherInfo map to LocalWeatherInfo correctly`(){
        val weatherInfo = getWeatherInfo()
        val cityName = "Saigon"
        val expectLocalWeatherInfo = getLocalWeatherInfo(cityName)

        val actualResult = weatherInfo.mapToLocalWeatherInfo(cityName)

        with(actualResult){
            assert(cityName == expectLocalWeatherInfo.cityName)
            assert(time == expectLocalWeatherInfo.time)
            assert(pressure == expectLocalWeatherInfo.pressure)
            assert(humidity == expectLocalWeatherInfo.humidity)
            assert(descriptions == expectLocalWeatherInfo.descriptions)
        }
    }

    @Test
    fun `verify LocalWeatherInfo map to ForecastResponse correctly`(){
        val cityName = "Saigon"
        val localWeatherInfo = getLocalWeatherInfo(cityName)

        val expectWeatherInfo = getWeatherInfo()

        val actualResult = localWeatherInfo.mapToWeatherInfo()

        with(actualResult){
            assert(time == expectWeatherInfo.time)
            assert(averageTemperature == expectWeatherInfo.averageTemperature)
            assert(pressure == expectWeatherInfo.pressure)
            assert(humidity == expectWeatherInfo.humidity)
            assert(description == expectWeatherInfo.description)
        }
    }

}