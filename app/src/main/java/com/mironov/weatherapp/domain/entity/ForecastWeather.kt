package com.mironov.weatherapp.domain.entity

import java.util.Calendar

data class ForecastWeather(
    val minTemp: Float,
    val maxTemp: Float,
    val date: Calendar,
    val condition: String,
    val conditionUrl: String,
)
