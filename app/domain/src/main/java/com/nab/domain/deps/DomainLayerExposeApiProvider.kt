package com.nab.domain.deps

import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase

interface DomainLayerExposeApiProvider {
    fun getDailyForecastByCityNameUseCase() : GetForecastDailyByCityNameUseCase

    fun clearWeatherForecastCachesUseCase() : ClearWeatherForecastCacheUseCase
}