package com.nab.data.remote.response

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("min") val min: Float = 0F,
    @SerializedName("max") val max: Float = 0F,
    @SerializedName("day") val day: Float = 0F,
    @SerializedName("night") val night: Float = 0F,
    @SerializedName("eve") val eve: Float = 0F,
    @SerializedName("morn") val morn: Float = 0F,
)