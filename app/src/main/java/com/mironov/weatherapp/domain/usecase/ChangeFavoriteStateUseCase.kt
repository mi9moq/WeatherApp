package com.mironov.weatherapp.domain.usecase

import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ChangeFavoriteStateUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    suspend fun add(city: City) = repository.add(city)

    suspend fun remove(id: Int) = repository.remove(id)
}