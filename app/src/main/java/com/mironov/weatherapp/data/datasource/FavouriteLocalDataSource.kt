package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavouriteLocalDataSource {

    fun getAll(): Flow<List<City>>

    suspend fun add(city: City)

    suspend fun remove(id: Int)

    fun isFavorite(id: Int): Flow<Boolean>
}