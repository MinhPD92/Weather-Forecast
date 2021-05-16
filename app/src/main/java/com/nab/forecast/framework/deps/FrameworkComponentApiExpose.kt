package com.nab.forecast.framework.deps

import com.nab.forecast.framework.dataStore.WeatherPreference

interface FrameworkComponentApiExpose {
    fun weatherPreferences() : WeatherPreference
}