package com.nab.domain.usecases

import com.nab.data.DailyWeatherForecastResult
import com.nab.data.repositories.ForecastRepository
import com.nab.domain.getForecastResponse
import com.nab.domain.models.WeatherInfo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetForecastDailyByCityNameUseCaseTest {

    @Mock
    private lateinit var forecastRepository: ForecastRepository

    private lateinit var getForecastDailyByCityNameUseCase: GetForecastDailyByCityNameUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(GetForecastDailyByCityNameUseCaseTest::class)

        getForecastDailyByCityNameUseCase =
            GetForecastDailyByCityNameUseCaseImpl(forecastRepository)
    }

    @Test
    fun `verify get daily forecast return DailyWeatherForecastError`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(forecastRepository.getDailyForecastByCityName(cityName)).thenReturn(
                flowOf(
                    DailyWeatherForecastResult.NoDataFoundError
                )
            )

            val result = getForecastDailyByCityNameUseCase.getDailyForecast(cityName)

            result.collect {
                assert(it is DailyWeatherForecastResult.DailyWeatherForecastError)
            }
        }
    }

    @Test
    fun `verify get daily forecast return DailyWeatherForecastSuccess with list WeatherInfo`() {
        runBlocking {
            val cityName = "Saigon"
            `when`(forecastRepository.getDailyForecastByCityName(cityName)).thenReturn(
                flowOf(
                    DailyWeatherForecastResult.DailyWeatherForecastSuccess(listOf(getForecastResponse()))
                )
            )

            val result = getForecastDailyByCityNameUseCase.getDailyForecast(cityName)

            result.collect {
                val data : List<WeatherInfo> = (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).repsonse
                assert(data.size == 1)

            }
        }
    }

}