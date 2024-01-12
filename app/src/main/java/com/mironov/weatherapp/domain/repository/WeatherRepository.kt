package com.mironov.weatherapp.domain.repository

import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.Weather

interface WeatherRepository {

    suspend fun getCurrent(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast
}