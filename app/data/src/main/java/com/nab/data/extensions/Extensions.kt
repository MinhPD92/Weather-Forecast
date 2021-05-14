package com.nab.data.extensions

import com.nab.data.DailyWeatherForecastResult
import com.nab.data.exceptionParser.parseException

internal inline fun <T : Any> runNetworkSafety(block: () -> DailyWeatherForecastResult<T>): DailyWeatherForecastResult<T> {
    runCatching {
        return block.invoke()
    }.onFailure {
        return it.parseException()
    }
    return DailyWeatherForecastResult.UnCatchError()
}