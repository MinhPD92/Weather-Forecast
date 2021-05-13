package com.nab.forecast

import android.app.Application
import com.nab.data.deps.DaggerDataLayerComponent
import com.nab.data.deps.DataLayerComponent
import com.nab.domain.deps.DaggerDomainLayerComponent
import com.nab.domain.deps.DomainLayerComponent
import com.nab.forecast.deps.ActivityInjectionProvider
import com.nab.forecast.deps.AppComponent
import com.nab.forecast.deps.DaggerAppComponent
import com.nab.forecast.deps.DependenciesInjectionProvider

class ForecastApplication : Application(), DependenciesInjectionProvider{

    private lateinit var dependenciesComponentInitDelegate : DependenciesComponentInitDelegate

    override fun onCreate() {
        super.onCreate()
        dependenciesComponentInitDelegate = DependenciesComponentInitDelegate(this)
    }

    override fun activityInjectionProvider(): ActivityInjectionProvider {
        return dependenciesComponentInitDelegate.appComponent
    }

}