package com.nab.domain.usecases

import com.nab.domain.DailyWeatherForecastResult
import com.nab.domain.getWeatherInfoList
import com.nab.domain.models.WeatherInfo
import com.nab.domain.repository.RemoteForecastRepository
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
    private lateinit var forecastRepository: RemoteForecastRepository

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
                    DailyWeatherForecastResult.DailyWeatherForecastSuccess(getWeatherInfoList())
                )
            )

            val result = getForecastDailyByCityNameUseCase.getDailyForecast(cityName)

            result.collect {
                val data : List<WeatherInfo> = (it as DailyWeatherForecastResult.DailyWeatherForecastSuccess).response
                assert(data.size == 1)

            }
        }
    }

}