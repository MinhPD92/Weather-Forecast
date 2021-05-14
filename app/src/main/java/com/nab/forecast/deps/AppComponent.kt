package com.nab.forecast.deps

import android.content.Context
import com.nab.data.local.LocalForecastService
import com.nab.data.repositories.LocalForecastRepository
import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.forecast.framework.dataStore.WeatherPreference
import com.nab.forecast.framework.deps.DatabaseModule
import com.nab.forecast.framework.deps.LocalServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, CommonModule::class, LocalServiceModule::class, DatabaseModule::class])
interface AppComponent : ActivityInjectionProvider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun dailyForecastByCityNameUseCase(dailyForecastUseCase: GetForecastDailyByCityNameUseCase): Builder

        @BindsInstance
        fun clearWeatherForecastCachesUseCase(useCase: ClearWeatherForecastCacheUseCase): Builder

        @BindsInstance
        fun weatherPreferences(weatherPreference: WeatherPreference): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}