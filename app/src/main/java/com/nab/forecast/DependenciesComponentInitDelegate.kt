package com.nab.forecast

import android.content.Context
import com.nab.configurations.deps.ConfigurationComponent
import com.nab.configurations.deps.DaggerConfigurationComponent
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
            .weatherPreferences(dataLayerComponent.weatherPreferences())
            .clearWeatherForecastCachesUseCase(domainLayerComponent.clearWeatherForecastCachesUseCase())
            .dailyForecastByCityNameUseCase(domainLayerComponent.getDailyForecastByCityNameUseCase())
            .build()
    }

    private val configurationComponent : ConfigurationComponent by lazy {
        DaggerConfigurationComponent.builder().build()
    }

    private val dataLayerComponent : DataLayerComponent by lazy {
        DaggerDataLayerComponent.builder()
            .context(context)
            .baseUrl(configurationComponent.baseUrl())
            .sslCerts(configurationComponent.sslCertificates())
            .appId(configurationComponent.appId())
            .build()
    }

    private val domainLayerComponent : DomainLayerComponent by lazy {
        DaggerDomainLayerComponent.builder()
            .localForecastRepository(dataLayerComponent.localForecastRepository())
            .remoteForecastRepository(dataLayerComponent.remoteForecastRepository())
            .build()
    }
}