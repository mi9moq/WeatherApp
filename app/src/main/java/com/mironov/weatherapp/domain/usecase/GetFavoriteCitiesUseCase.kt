package com.mironov.weatherapp.domain.usecase

import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteCitiesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke(): Flow<List<City>> = repository.getAll()
}