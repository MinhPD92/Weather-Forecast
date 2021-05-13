package com.nab.forecast

import android.content.Context
import com.nab.data.deps.DaggerDataLayerComponent
import com.nab.data.deps.DataLayerComponent
import com.nab.domain.deps.DaggerDomainLayerComponent
import com.nab.domain.deps.DomainLayerComponent
import com.nab.forecast.deps.AppComponent
import com.nab.forecast.deps.DaggerAppComponent

class DependenciesComponentInitDelegate constructor(private val context: Context) {
    internal val appComponent : AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(context)
            .dailyForecastByCityNameUseCase(domainLayerComponent.getDailyForecastByCityNameUseCase())
            .build()
    }

    private val dataLayerComponent : DataLayerComponent by lazy {
        DaggerDataLayerComponent.builder().build()
    }

    private val domainLayerComponent : DomainLayerComponent by lazy {
        DaggerDomainLayerComponent.builder()
            .forecastRepository(dataLayerComponent.forecastRepository())
            .build()
    }
}