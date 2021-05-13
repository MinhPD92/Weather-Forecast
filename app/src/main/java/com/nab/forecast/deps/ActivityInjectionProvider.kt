package com.nab.forecast.deps

import com.nab.forecast.presentation.MainActivity

interface ActivityInjectionProvider {
    fun inject(mainActivity: MainActivity)
}