package com.nab.forecast.framework.room.repository

import com.nab.data.DailyWeatherForecastResult
import com.nab.data.remote.response.ForecastResponse
import com.nab.forecast.*
import com.nab.forecast.framework.room.WeatherForecastDAO
import com.nab.forecast.framework.room.WeatherForecastDatabase
import com.nab.forecast.framework.room.model.LocalWeatherInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalWeatherForecastRepositoryTest {

    @Mock
    private lateinit var weatherDatabase: WeatherForecastDatabase

    @Mock
    private lateinit var weatherForecastDAO: WeatherForecastDAO

    @Captor
    private lateinit var localWeatherInfoCaptor: ArgumentCaptor<List<LocalWeatherInfo>>

    private lateinit var repository: LocalWeatherForecastRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(LocalWeatherForecastRepositoryTest::class)
        `when`(weatherDatabase.weatherForecastDao()).thenReturn(weatherForecastDAO)
        repository = LocalWeatherForecastRepositoryImpl(weatherDatabase)
    }

    @Test
    fun `verify database call clear all caches`() {
        runBlocking {
            repository.clearAllWeatherForecastCaches()
            verify(weatherDatabase.weatherForecastDao()).clearAllCache()
        }
    }

    @Test
    fun `verify database call add data into database`() {
        runBlocking {
            val cityName = "Saigon"
            val mockData = listOf(getForecastResponse())
            repository.cacheCityWeatherForecast(forecastResponses = mockData, cityName = cityName)
            verify(weatherDatabase.weatherForecastDao()).cacheCityWeatherForecast(
                com.nhaarman.mockitokotlin2.capture(
                    localWeatherInfoCaptor
                )
            )

            val cacheData = localWeatherInfoCaptor.value
            assert(cacheData.size == 1)
            val localWeatherInfo = cacheData[0]
            with(localWeatherInfo) {
                assert(this.cityName == cityName)
                assert(tempMax == MAX_TEMP)
                assert(tempMin == MIN_TEMP)
                assert(humidity == HUMIDITY)
                assert(pressure == PRESSURE)
            }
        }
    }

    @Test
    fun `verify get daily forecast by city name return NoDataFoundError when have no cache of city`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(weatherDatabase.weatherForecastDao().getWeatherForecastByCityName(cityName))
                .thenReturn(listOf())

            val result = repository.getDailyForecastByCityName(cityName = cityName)
            result.collect {
                assert(it is DailyWeatherForecastResult.NoDataFoundError)
            }
            verify(weatherDatabase.weatherForecastDao()).getWeatherForecastByCityName(cityName = cityName)
        }
    }

    @Test
    fun `verify get daily forecast by city name return valid ForecastResponse data`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(weatherDatabase.weatherForecastDao().getWeatherForecastByCityName(cityName))
                .thenReturn(listOf(getLocalWeatherInfo(cityName)))

            val result = repository.getDailyForecastByCityName(cityName = cityName)
            result.collect {
                assert(it is DailyWeatherForecastResult.DailyWeatherForecastSuccess)
                val forecastResponseList: List<ForecastResponse> =
                    (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).repsonse

                assert(forecastResponseList.size == 1)
            }
            verify(weatherDatabase.weatherForecastDao()).getWeatherForecastByCityName(cityName = cityName)
        }
    }
}