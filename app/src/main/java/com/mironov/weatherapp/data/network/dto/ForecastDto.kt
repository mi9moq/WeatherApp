package com.mironov.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday")
    val forecastWeather: List<ForecastWeatherDto>
)
