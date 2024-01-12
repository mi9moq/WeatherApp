package com.mironov.weatherapp.domain.usecase

import com.mironov.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {

    operator fun invoke(id: Int) = repository.isFavorite(id)
}