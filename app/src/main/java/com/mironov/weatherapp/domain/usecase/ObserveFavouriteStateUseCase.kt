package com.mironov.weatherapp.domain.usecase

import com.mironov.weatherapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {

    operator fun invoke(id: Int) = repository.isFavorite(id)
}