package com.nab.configurations.deps

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ConfigurationModule::class])
interface ConfigurationComponent : ConfigurationComponentExposeApi {

    @Component.Builder
    interface Builder {

        fun build(): ConfigurationComponent
    }
}