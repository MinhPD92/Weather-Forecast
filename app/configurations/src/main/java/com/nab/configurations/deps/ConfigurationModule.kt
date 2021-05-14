package com.nab.configurations.deps

import com.nab.configurations.BuildConfig
import com.nab.configurations.configs.SSLCertificates
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

const val APP_ID = "ConfigurationModule_APP_ID"
const val BASE_URL = "ConfigurationModule_BASE_URL"

@Module
class ConfigurationModule {

    @Provides
    @Singleton
    @Named(APP_ID)
    fun provideAppId() : String{
        return BuildConfig.APP_ID
    }

    @Provides
    @Singleton
    fun provideSSLCertificates(): SSLCertificates {
        return SSLCertificates(
            domainPattern = BuildConfig.DOMAIN_PATTERN,
            cert1 = BuildConfig.SSL_CERT_1,
            cert2 = BuildConfig.SSL_CERT_2,
            cert3 = BuildConfig.SSL_CERT_3
        )
    }

    @Provides
    @Singleton
    @Named(BASE_URL)
    fun provideBaseURL() : String{
        return BuildConfig.BASE_URL
    }

}