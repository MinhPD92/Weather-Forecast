package com.nab.domain.deps

import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.data.repositories.ForecastRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface DomainLayerComponent : DomainLayerExposeApiProvider {

    override fun getDailyForecastByCityNameUseCase(): GetForecastDailyByCityNameUseCase

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun forecastRepository(forecastRepository: ForecastRepository) : Builder

        fun build() : DomainLayerComponent
    }
}