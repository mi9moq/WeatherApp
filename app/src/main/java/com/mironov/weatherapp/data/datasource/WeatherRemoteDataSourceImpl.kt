package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.data.converter.WeatherConverter
import com.mironov.weatherapp.data.network.api.WeatherApi
import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.Weather
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val api: WeatherApi,
    private val converter: WeatherConverter
) : WeatherRemoteDataSource {

    private companion object {
        const val PREFIX = "id:"
    }

    override suspend fun getCurrent(cityId: Int): Weather =
        api.loadCurrentWeather("$PREFIX$cityId").let(converter::weatherResponseToEntity)

    override suspend fun getForecast(cityId: Int): Forecast =
        api.loadForecastWeather("$PREFIX$cityId").let(converter::forecastResponseToEntity)
}