package com.nab.domain.deps

import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetForecastDailyByCityNameUseCase(useCase : GetForecastDailyByCityNameUseCaseImpl): GetForecastDailyByCityNameUseCase
}