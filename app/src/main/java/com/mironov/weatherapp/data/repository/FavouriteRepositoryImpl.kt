package com.mironov.weatherapp.data.repository

import com.mironov.weatherapp.data.datasource.FavouriteLocalDataSource
import com.mironov.weatherapp.di.IoDispatcher
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.domain.repository.FavouriteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val dataSource: FavouriteLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FavouriteRepository {

    override fun getAll(): Flow<List<City>> =
        dataSource.getAll()

    override suspend fun add(city: City) {
        withContext(dispatcher) {
            dataSource.add(city)
        }
    }

    override suspend fun remove(id: Int) {
        withContext(dispatcher) {
            dataSource.remove(id)
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> =
        dataSource.isFavorite(id)
}