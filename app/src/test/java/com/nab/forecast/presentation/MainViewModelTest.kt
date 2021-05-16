package com.nab.forecast.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nab.domain.DailyWeatherForecastResult
import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.forecast.coroutineTestRule.CoroutineTestRule
import com.nab.data.dataStore.WeatherPreference
import com.nab.forecast.getWeatherInfoList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var mainViewModel: MainViewModel

    @Mock
    private lateinit var getForecastDailyByCityNameUseCase: GetForecastDailyByCityNameUseCase

    @Mock
    private lateinit var clearWeatherForecastCacheUseCase: ClearWeatherForecastCacheUseCase

    @Mock
    private lateinit var weatherPreference: WeatherPreference

    @Mock
    private lateinit var stateObserver: Observer<MainState>

    @Captor
    private lateinit var argumentCaptor : ArgumentCaptor<MainState.ErrorState>


    @Before
    fun init() {
        MockitoAnnotations.openMocks(MainViewModelTest::class)

        mainViewModel = MainViewModel(
            dispatcherProvider = coroutineTestRule.testDispatcherProvider,
            forecastDailyByCityNameUseCase = getForecastDailyByCityNameUseCase,
            clearWeatherForecastCacheUseCase = clearWeatherForecastCacheUseCase,
            weatherPreference = weatherPreference
        )
    }

    @Test
    fun `verify cache does not be cleared because the time still in same day`() {

        val todayTime = 123456L
        runBlockingTest {
            `when`(weatherPreference.isOnNewDay(time = todayTime)).thenReturn(flowOf(false))

            mainViewModel.clearWeatherForecastCacheIfNeed(todayTime)

            verify(clearWeatherForecastCacheUseCase, times(0)).clearAllWeatherForecastCaches()
        }
    }

    @Test
    fun `verify cache is cleared because the time still in on new day`() {
        val todayTime = 123456L
        runBlockingTest {
            `when`(weatherPreference.isOnNewDay(time = todayTime)).thenReturn(flowOf(true))

            mainViewModel.clearWeatherForecastCacheIfNeed(todayTime)

            verify(clearWeatherForecastCacheUseCase).clearAllWeatherForecastCaches()
        }
    }

    @Test
    fun `verify get daily weather forecast by city name succeed with list data response`() {

        runBlockingTest {
            mainViewModel.state.observeForever(stateObserver)
            val cityName = "Saigon"
            `when`(getForecastDailyByCityNameUseCase.getDailyForecast(cityName)).thenReturn(
                flowOf(DailyWeatherForecastResult.DailyWeatherForecastSuccess(getWeatherInfoList()))
            )

            mainViewModel.getDailyWeatherForecast(cityName)
            verify(stateObserver).onChanged(MainState.LoadingState)
            verify(getForecastDailyByCityNameUseCase).getDailyForecast(cityName)
            verify(stateObserver).onChanged(any(MainState.SucceedState::class.java))

            mainViewModel.state.removeObserver(stateObserver)
        }
    }

    @Test
    fun `verify get daily weather forecast by city name return no data error`(){
        runBlockingTest {
            mainViewModel.state.observeForever(stateObserver)
            val cityName = "Saigon"
            `when`(getForecastDailyByCityNameUseCase.getDailyForecast(cityName)).thenReturn(
                flowOf(DailyWeatherForecastResult.NoDataFoundError)
            )

            mainViewModel.getDailyWeatherForecast(cityName)
            verify(stateObserver).onChanged(MainState.LoadingState)

            verify(getForecastDailyByCityNameUseCase).getDailyForecast(cityName)

            // 2 times, 1 for loading state and 1 for result
            verify(stateObserver, times(2)).onChanged(argumentCaptor.capture())
            assert(argumentCaptor.value.errorType is DailyWeatherForecastResult.NoDataFoundError)

            mainViewModel.state.removeObserver(stateObserver)
        }
    }

    @Test
    fun `verify get daily weather forecast by city name return network error`(){
        runBlockingTest {
            mainViewModel.state.observeForever(stateObserver)
            val cityName = "Saigon"
            `when`(getForecastDailyByCityNameUseCase.getDailyForecast(cityName)).thenReturn(
                flowOf(DailyWeatherForecastResult.NetWorkError)
            )

            mainViewModel.getDailyWeatherForecast(cityName)
            verify(stateObserver).onChanged(MainState.LoadingState)

            verify(getForecastDailyByCityNameUseCase).getDailyForecast(cityName)

            // 2 times, 1 for loading state and 1 for result
            verify(stateObserver, times(2)).onChanged(argumentCaptor.capture())
            assert(argumentCaptor.value.errorType is DailyWeatherForecastResult.NetWorkError)

            mainViewModel.state.removeObserver(stateObserver)
        }
    }

    @Test
    fun `verify get daily weather forecast by city name return un-catch error`(){
        runBlockingTest {
            mainViewModel.state.observeForever(stateObserver)
            val cityName = "Saigon"
            val errorMessage = "error message"
            val errorCode = 400
            `when`(getForecastDailyByCityNameUseCase.getDailyForecast(cityName)).thenReturn(
                flowOf(DailyWeatherForecastResult.UnCatchError(errorMessage = errorMessage, errorCode = errorCode))
            )

            mainViewModel.getDailyWeatherForecast(cityName)
            verify(stateObserver).onChanged(MainState.LoadingState)

            verify(getForecastDailyByCityNameUseCase).getDailyForecast(cityName)

            // 2 times, 1 for loading state and 1 for result
            verify(stateObserver, times(2)).onChanged(argumentCaptor.capture())
            assert((argumentCaptor.value.errorType as DailyWeatherForecastResult.UnCatchError).errorCode == errorCode)
            assert((argumentCaptor.value.errorType as DailyWeatherForecastResult.UnCatchError).errorMessage == errorMessage)

            mainViewModel.state.removeObserver(stateObserver)
        }
    }

}