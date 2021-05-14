package com.nab.domain.deps

import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.data.repositories.ForecastRepository
import com.nab.data.repositories.LocalForecastRepository
import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface DomainLayerComponent : DomainLayerExposeApiProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun forecastRepository(forecastRepository: ForecastRepository): Builder

        @BindsInstance
        fun localForecastRepository(localForecastRepository: LocalForecastRepository): Builder

        fun build(): DomainLayerComponent
    }
}