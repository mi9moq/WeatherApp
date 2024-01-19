package com.mironov.weatherapp.presenation.favourite

import com.arkivanov.mvikotlin.core.store.Store
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.favourite.FavouriteStore.Intent
import com.mironov.weatherapp.presenation.favourite.FavouriteStore.Label
import com.mironov.weatherapp.presenation.favourite.FavouriteStore.State

internal interface FavouriteStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickSearch : Intent

        data object ClickToFavourite : Intent

        data class CityItemClicked(val city: City) : Intent
    }

    data class State(
        val cityItems: List<CityItem>
    ) {

        data class CityItem(
            val city: City,
            val weatherState: WeatherState
        )

        sealed interface WeatherState {

            data object Initial : WeatherState

            data object Loading : WeatherState

            data object Error : WeatherState

            data class Loaded(
                val temp: Float,
                val iconUrl: String
            ) : WeatherState
        }
    }

    sealed interface Label {

        data object ClickSearch : Label

        data object ClickToFavourite : Label

        data class CityItemClicked(val city: City) : Label
    }
}