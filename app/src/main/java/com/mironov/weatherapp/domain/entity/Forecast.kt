package com.mironov.weatherapp.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<ForecastWeather>,
)
