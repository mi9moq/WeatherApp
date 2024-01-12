package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.data.converter.CityConverter
import com.mironov.weatherapp.data.network.api.WeatherApi
import com.mironov.weatherapp.domain.entity.City
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val api: WeatherApi,
    private val converter: CityConverter
) : SearchRemoteDataSource {
    override suspend fun searchCities(query: String): List<City> =
        api.searchCity(query).map(converter::dtoToEntity)
}