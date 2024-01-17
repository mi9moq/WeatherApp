package com.mironov.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeather: WeatherDto
)
