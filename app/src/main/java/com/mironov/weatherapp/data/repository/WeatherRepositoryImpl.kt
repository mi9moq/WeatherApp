package com.mironov.weatherapp.data.repository

import com.mironov.weatherapp.data.datasource.WeatherRemoteDataSource
import com.mironov.weatherapp.di.IoDispatcher
import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.Weather
import com.mironov.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : WeatherRepository {

    override suspend fun getCurrent(cityId: Int): Weather = withContext(dispatcher) {
        dataSource.getCurrent(cityId)
    }

    override suspend fun getForecast(cityId: Int): Forecast = withContext(dispatcher) {
        dataSource.getForecast(cityId)
    }
}