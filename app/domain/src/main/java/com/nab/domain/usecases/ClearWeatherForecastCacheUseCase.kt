package com.nab.domain.usecases

import com.nab.domain.repository.LocalForecastRepository
import javax.inject.Inject

interface ClearWeatherForecastCacheUseCase {
    suspend fun clearAllWeatherForecastCaches()
}

class ClearWeatherForecastCacheUseCaseImpl @Inject constructor(private val repository: LocalForecastRepository) :
    ClearWeatherForecastCacheUseCase {

    override suspend fun clearAllWeatherForecastCaches() {
        repository.clearAllWeatherForecastCaches()
    }

}