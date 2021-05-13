package com.nab.data.deps

import com.nab.data.repositories.ForecastRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppConfigurationModule::class, RepositoryModule::class])
interface DataLayerComponent : DataLayerExposeApiProvider {

    override fun forecastRepository(): ForecastRepository

    @Component.Builder
    interface Builder {

        fun build(): DataLayerComponent
    }
}