package com.nab.data.deps

import com.nab.data.local.LocalForecastService
import com.nab.domain.repository.LocalForecastRepository
import com.nab.data.room.repositories.LocalWeatherForecastRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class LocalServiceModule {

    @Binds
    abstract fun bindLocalForecastService(service: LocalWeatherForecastRepositoryImpl) : LocalForecastService

    @Binds
    abstract fun bindLocalForecastRepository(repositoryImpl: LocalWeatherForecastRepositoryImpl) : LocalForecastRepository
}