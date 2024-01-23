package com.mironov.weatherapp.presenation.search

import com.arkivanov.mvikotlin.core.store.Store
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.search.SearchStore.Intent
import com.mironov.weatherapp.presenation.search.SearchStore.Label
import com.mironov.weatherapp.presenation.search.SearchStore.State

internal interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class ClickSearchItem(val cityId: Int): Intent

        data class ChangeSearchQuery(val query: String): Intent

        data object ClickBack: Intent

        data object ClickSearch: Intent
    }

    data class State(
        val searchQuery: String,
        val searchState: SearchState,
    ) {

        sealed interface SearchState{

            data object Initial: SearchState

            data object Loading : SearchState

            data object Error: SearchState

            data object EmptyResult : SearchState

            data class Content(val cities: List<City>) : SearchState
        }
    }

    sealed interface Label {

        data object ClickBack: Label

        data object SavedToFavourite: Label

        data class OpenForecast(val city: City): Label
    }
}
