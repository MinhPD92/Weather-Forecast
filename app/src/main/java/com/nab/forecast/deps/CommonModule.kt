package com.nab.forecast.deps

import com.nab.forecast.dispatcherProvider.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    @Singleton
    fun providerDispatcherProvider(): DispatcherProvider{
        return DispatcherProvider()
    }
}