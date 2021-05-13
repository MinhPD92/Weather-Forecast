package com.nab.data.deps

import com.nab.data.repositories.ForecastRepository

interface DataLayerExposeApiProvider {
    fun forecastRepository() : ForecastRepository
}