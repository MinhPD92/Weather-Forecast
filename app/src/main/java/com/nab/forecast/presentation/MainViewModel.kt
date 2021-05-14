package com.nab.forecast.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.forecast.dispatcherProvider.DispatcherProvider
import com.nab.forecast.framework.dataStore.WeatherPreference
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val forecastDailyByCityNameUseCase: GetForecastDailyByCityNameUseCase,
    private val clearWeatherForecastCacheUseCase: ClearWeatherForecastCacheUseCase,
    private val weatherPreference: WeatherPreference
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    internal val state: LiveData<MainState> = _state
    private var queryJob: Job? = null

    internal fun getDailyWeatherForecast(cityName: String) {
        _state.postValue(MainState.LoadingState)
        queryJob?.cancel()
        queryJob = viewModelScope.launch {
            forecastDailyByCityNameUseCase.getDailyForecast(cityName)
                .flowOn(dispatcherProvider.io())
                .catch {
                    _state.postValue(MainState.ErrorState(errorMessage = it.message))
                }.collect {
                    _state.postValue(MainState.SucceedState(it))
                }
        }
    }

    internal fun clearWeatherForecastCacheIfNeed(time: Long) {

        viewModelScope.launch(dispatcherProvider.io()) {
            weatherPreference.isOnNewDay(time).collect { isNewDay ->
                if (isNewDay) {
                    clearWeatherForecastCacheUseCase.clearAllWeatherForecastCaches()
                }
            }

        }
    }
}