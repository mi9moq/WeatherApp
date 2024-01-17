package com.mironov.weatherapp.data.repository

import com.mironov.weatherapp.data.datasource.SearchRemoteDataSource
import com.mironov.weatherapp.di.IoDispatcher
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val dataSource: SearchRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SearchRepository {

    override suspend fun searchCity(query: String): List<City> = withContext(dispatcher) {
        dataSource.searchCities(query)
    }
}