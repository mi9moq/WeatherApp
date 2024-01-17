package com.mironov.weatherapp.data.network.dto

data class ForecastWeatherResponse(
    val current: WeatherDto,
    val forecast: ForecastDto,
)
