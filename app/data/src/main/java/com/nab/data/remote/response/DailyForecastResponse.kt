package com.nab.data.remote.response

import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    @SerializedName("cnt") val count: String,
    @SerializedName("list") val response: List<ForecastResponse>
)