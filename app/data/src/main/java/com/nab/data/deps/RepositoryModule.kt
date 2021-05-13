package com.nab.data.deps

import com.nab.data.repositories.ForecastRepository
import com.nab.data.repositories.ForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindForecastRepository(repo: ForecastRepositoryImpl) : ForecastRepository

}