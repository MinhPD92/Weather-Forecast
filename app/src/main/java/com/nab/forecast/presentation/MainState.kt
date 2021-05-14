package com.nab.forecast.presentation

import com.nab.data.DailyWeatherForecastResult
import com.nab.domain.models.WeatherInfo

sealed class MainState {

    object LoadingState : MainState()

    class ErrorState(val errorType: DailyWeatherForecastResult.DailyWeatherForecastError) : MainState()

    class SucceedState(val results: List<WeatherInfo>) : MainState()
}