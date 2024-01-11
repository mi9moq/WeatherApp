package com.mironov.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastWeatherDto(
    @SerializedName("maxtemp_c")
    val maxTemp: Float,
    @SerializedName("mintemp_c")
    val minTemp: Float,
    val condition: ConditionDto
)
