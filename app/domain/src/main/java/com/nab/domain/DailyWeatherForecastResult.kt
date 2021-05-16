package com.nab.domain

sealed class DailyWeatherForecastResult<out T> {

    class DailyWeatherForecastSuccess<out T : Any>(val response : T) : DailyWeatherForecastResult<T>()

    open class DailyWeatherForecastError() : DailyWeatherForecastResult<Nothing>()

    object NoDataFoundError : DailyWeatherForecastError()

    object NetWorkError : DailyWeatherForecastError()

    class UnCatchError(val errorMessage : String? = null, val errorCode: Int? = null) : DailyWeatherForecastError()

}