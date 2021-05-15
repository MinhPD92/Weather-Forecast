package com.nab.forecast.framework.room.mappers

import com.nab.domain.DESCRIPTION_SEPARATOR
import com.nab.forecast.*
import com.nab.forecast.framework.room.model.LocalWeatherInfo
import org.junit.Test

class LocalWeatherForecastMapperTest {

    @Test
    fun `verify ForecastResponse map to LocalWeatherInfo correctly`(){
        val forecastResponse = getForecastResponse()
        val cityName = "Saigon"
        val expectLocalWeatherInfo = getLocalWeatherInfo(cityName)

        val actualResult = forecastResponse.mapToLocalWeatherInfo(cityName)

        with(actualResult){
            assert(cityName == expectLocalWeatherInfo.cityName)
            assert(time == expectLocalWeatherInfo.time)
            assert(tempMin == expectLocalWeatherInfo.tempMin)
            assert(tempMax == expectLocalWeatherInfo.tempMax)
            assert(pressure == expectLocalWeatherInfo.pressure)
            assert(humidity == expectLocalWeatherInfo.humidity)
            assert(descriptions == expectLocalWeatherInfo.descriptions)
        }
    }

    @Test
    fun `verify LocalWeatherInfo map to ForecastResponse correctly`(){
        val cityName = "Saigon"
        val localWeatherInfo = getLocalWeatherInfo(cityName)

        val expectForecastResponse = getForecastResponse()

        val actualResult = localWeatherInfo.mapToForecastResponse()

        with(actualResult){
            assert(dt == expectForecastResponse.dt)
            assert(temp.min == expectForecastResponse.temp.min)
            assert(temp.max == expectForecastResponse.temp.max)
            assert(pressure == expectForecastResponse.pressure)
            assert(humidity == expectForecastResponse.humidity)
            assert(weather.size == 2)
            assert(weather[0].description == "${DESCRIPTION}0")
            assert(weather[1].description == "${DESCRIPTION}1")
        }
    }

}