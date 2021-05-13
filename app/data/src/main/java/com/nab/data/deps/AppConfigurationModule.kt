package com.nab.data.deps

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

const val APP_ID = "CONFIGURATION_APP_ID"
@Module
class AppConfigurationModule {

    @Provides
    @Singleton
    @Named(APP_ID)
    fun provideAppId() = "60c6fbeb4b93ac653c492ba806fc346d"
}