package com.nab.forecast.presentation

import com.nab.domain.models.WeatherInfo

sealed class MainState {

    object LoadingState : MainState()

    class ErrorState(val errorMessage: String?) : MainState()

    class SucceedState(val results: List<WeatherInfo>) : MainState()
}