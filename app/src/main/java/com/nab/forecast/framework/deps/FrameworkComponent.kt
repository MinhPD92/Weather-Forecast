package com.nab.forecast.framework.deps

import android.content.Context
import com.nab.data.deps.DatabaseModule
import com.nab.data.deps.LocalServiceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PreferencesModule::class])
interface FrameworkComponent : FrameworkComponentApiExpose {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build() : FrameworkComponent
    }
}