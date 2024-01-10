package com.mironov.weatherapp.domain.repository

import com.mironov.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun get(): Flow<List<City>>

    suspend fun add(city: City)

    suspend fun remove(id: Int)

    fun isFavorite(id: Int): Flow<Boolean>
}