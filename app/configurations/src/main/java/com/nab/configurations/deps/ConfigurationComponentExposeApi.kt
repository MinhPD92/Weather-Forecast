package com.nab.configurations.deps

import com.nab.configurations.configs.SSLCertificates
import javax.inject.Named

interface ConfigurationComponentExposeApi {
    fun sslCertificates() : SSLCertificates

    @Named(BASE_URL)
    fun baseUrl() : String

    @Named(APP_ID)
    fun appId() : String
}