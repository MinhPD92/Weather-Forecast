package com.nab.data.repositories

import com.nab.domain.DailyWeatherForecastResult
import com.nab.data.getForecastResponse
import com.nab.data.getWeatherInfo
import com.nab.data.local.LocalForecastService
import com.nab.data.remote.ForecastService
import com.nab.data.remote.response.DailyForecastResponse
import com.nab.domain.repository.RemoteForecastRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastRepositoryTest {

    @Mock
    private lateinit var forecastService: ForecastService

    @Mock
    private lateinit var localForecastService: LocalForecastService

    private val appId = "AppID"

    private lateinit var repository: RemoteForecastRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(ForecastRepositoryTest::class)
        repository = RemoteForecastRepositoryImpl(
            forecastService = forecastService,
            localService = localForecastService,
            appId = appId
        )
    }

    @Test
    fun `verify get daily weather forecast by city name return valid data from cache`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(localForecastService.getDailyForecastByCityName(cityName))
                .thenReturn(
                    flowOf(
                        DailyWeatherForecastResult.DailyWeatherForecastSuccess(
                            listOf(
                               getWeatherInfo()
                            )
                        )
                    )
                )

            val resultFlow = repository.getDailyForecastByCityName(cityName)
            resultFlow.collect {
                assert(it is DailyWeatherForecastResult.DailyWeatherForecastSuccess)
                val dataList =
                    (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).response
                assert(dataList.size == 1)
            }

            verify(localForecastService).getDailyForecastByCityName(cityName)
            verify(localForecastService, times(0)).cacheCityWeatherForecast(weatherInfoList = anyList(), cityName = anyString())
            verify(forecastService, times(0)).searchDailyForecastByCityName(key = cityName, appId = appId)
        }
    }

    @Test
    fun `verify cache return error state then remote return valid data`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(localForecastService.getDailyForecastByCityName(cityName))
                .thenReturn(
                    flowOf(DailyWeatherForecastResult.NoDataFoundError)
                )

            `when`(forecastService.searchDailyForecastByCityName(key = cityName, appId = appId))
                .thenReturn(DailyForecastResponse("1", listOf(getForecastResponse())))

            val resultFlow = repository.getDailyForecastByCityName(cityName)
            resultFlow.collect {
                assert(it is DailyWeatherForecastResult.DailyWeatherForecastSuccess)
                val dataList =
                    (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).response
                assert(dataList.size == 1)
            }

            verify(localForecastService).getDailyForecastByCityName(cityName)
            verify(forecastService).searchDailyForecastByCityName(key = cityName, appId = appId)
        }
    }

    @Test
    fun `verify local service cache data after get data from api successfully`() {
        runBlocking {
            val mockForecastResponse = listOf(getForecastResponse())
            val cityName = "Saigon"
            `when`(localForecastService.getDailyForecastByCityName(cityName))
                .thenReturn(
                    flowOf(DailyWeatherForecastResult.NoDataFoundError)
                )

            `when`(forecastService.searchDailyForecastByCityName(key = cityName, appId = appId))
                .thenReturn(DailyForecastResponse("1", mockForecastResponse))

            val resultFlow = repository.getDailyForecastByCityName(cityName)
            resultFlow.collect {
                assert(it is DailyWeatherForecastResult.DailyWeatherForecastSuccess)
                val dataList =
                    (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).response
                assert(dataList.size == 1)

                verify(localForecastService).cacheCityWeatherForecast(
                    weatherInfoList = dataList,
                    cityName = cityName
                )
            }
        }
    }
}