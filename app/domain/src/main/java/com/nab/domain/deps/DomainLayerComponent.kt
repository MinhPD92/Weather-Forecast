package com.nab.domain.deps

import com.nab.domain.repository.RemoteForecastRepository
import com.nab.domain.repository.LocalForecastRepository
import dagger.BindsInstance
import dagger.Component

@Component(modules = [UseCaseModule::class])
interface DomainLayerComponent : DomainLayerExposeApiProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun remoteForecastRepository(remoteForecastRepository: RemoteForecastRepository): Builder

        @BindsInstance
        fun localForecastRepository(localForecastRepository: LocalForecastRepository): Builder

        fun build(): DomainLayerComponent
    }
}