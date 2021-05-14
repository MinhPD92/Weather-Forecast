package com.nab.forecast.framework.deps

import com.nab.data.local.LocalForecastService
import com.nab.data.repositories.LocalForecastRepository
import com.nab.forecast.framework.room.LocalWeatherForecastRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class LocalServiceModule {

    @Binds
    abstract fun bindLocalForecastService(service: LocalWeatherForecastRepositoryImpl) : LocalForecastService

    @Binds
    abstract fun bindLocalForecastRepository(repositoryImpl: LocalWeatherForecastRepositoryImpl) : LocalForecastRepository
}