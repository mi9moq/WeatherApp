package com.mironov.weatherapp.domain.entity

import java.time.LocalDateTime

data class Weather(
    val temperature: Float,
    val date: LocalDateTime,
    val condition: String,
    val conditionUrl: String,
)
