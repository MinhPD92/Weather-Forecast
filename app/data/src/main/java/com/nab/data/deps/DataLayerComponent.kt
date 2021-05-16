package com.nab.data.deps

import android.content.Context
import com.nab.configurations.configs.SSLCertificates
import com.nab.configurations.deps.APP_ID
import com.nab.configurations.deps.BASE_URL
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
    RemoteRepositoryModule::class,
    DatabaseModule::class,
    PreferencesModule::class,
    LocalServiceModule::class])
interface DataLayerComponent : DataLayerExposeApiProvider {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun baseUrl(@Named(BASE_URL) baseUrl: String) : Builder

        @BindsInstance
        fun appId(@Named(APP_ID) appId: String) : Builder

        @BindsInstance
        fun sslCerts(sslCertificates: SSLCertificates) : Builder

        fun build(): DataLayerComponent
    }
}