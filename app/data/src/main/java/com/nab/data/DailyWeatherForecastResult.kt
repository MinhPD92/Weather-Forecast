package com.nab.data

import com.nab.data.remote.response.ForecastResponse
import java.lang.Exception

sealed class DailyWeatherForecastResult<out T> {

    class DailyWeatherForecastSuccess<out T : Any>(val repsonse : T) : DailyWeatherForecastResult<T>()

    open class DailyWeatherForecastError() : DailyWeatherForecastResult<Nothing>()

    object NoDataFoundError : DailyWeatherForecastError()

    object NetWorkError : DailyWeatherForecastError()

    class UnCatchError(val errorMessage : String? = null, val errorCode: Int? = null) : DailyWeatherForecastError()

}