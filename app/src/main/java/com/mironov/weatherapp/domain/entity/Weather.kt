package com.mironov.weatherapp.domain.entity

import java.util.Calendar

data class Weather(
    val temp: Float,
    val date: Calendar,
    val condition: String,
    val conditionUrl: String,
)
