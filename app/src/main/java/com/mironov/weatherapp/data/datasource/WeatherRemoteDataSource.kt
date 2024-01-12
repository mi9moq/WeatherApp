package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.Weather

interface WeatherRemoteDataSource {

    suspend fun getCurrent(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast
}