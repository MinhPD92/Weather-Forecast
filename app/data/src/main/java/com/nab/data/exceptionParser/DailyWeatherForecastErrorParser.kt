package com.nab.data.exceptionParser

import android.accounts.NetworkErrorException
import com.nab.domain.DailyWeatherForecastResult
import com.nab.data.constants.NO_DATA_FOUND_ERROR_CODE
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun <T : Any> Throwable.parseException(): DailyWeatherForecastResult<T> {
    return when (this) {
        is NetworkErrorException, is IOException, is UnknownHostException -> {
            DailyWeatherForecastResult.NetWorkError
        }

        is HttpException -> {
            if (code() == NO_DATA_FOUND_ERROR_CODE) {
                DailyWeatherForecastResult.NoDataFoundError
            } else {
                DailyWeatherForecastResult.UnCatchError(errorMessage = message, errorCode = code())
            }
        }
        else -> DailyWeatherForecastResult.UnCatchError(errorMessage = message)
    }
}