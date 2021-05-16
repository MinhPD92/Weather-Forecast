package com.nab.data.deps

import com.nab.data.repositories.RemoteForecastRepositoryImpl
import com.nab.domain.repository.RemoteForecastRepository
import dagger.Binds
import dagger.Module

@Module
internal abstract class RemoteRepositoryModule {

    @Binds
    abstract fun bindForecastRepository(repo: RemoteForecastRepositoryImpl) : RemoteForecastRepository

}