package com.nab.data.deps

import com.nab.data.local.LocalForecastService
import com.nab.data.repositories.ForecastRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppConfigurationModule::class, RepositoryModule::class])
interface DataLayerComponent : DataLayerExposeApiProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun localWeatherForecastService(localForecastService: LocalForecastService): Builder

        fun build(): DataLayerComponent
    }
}