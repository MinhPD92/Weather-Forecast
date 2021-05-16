package com.nab.forecast.deps

import android.content.Context
import com.nab.domain.usecases.ClearWeatherForecastCacheUseCase
import com.nab.domain.usecases.GetForecastDailyByCityNameUseCase
import com.nab.forecast.framework.dataStore.WeatherPreference
import com.nab.data.deps.DatabaseModule
import com.nab.data.deps.LocalServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, CommonModule::class])
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