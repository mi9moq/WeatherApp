package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.domain.entity.City

interface SearchRemoteDataSource {

    suspend fun searchCities(query: String): List<City>
}