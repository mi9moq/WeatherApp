package com.mironov.weatherapp.presenation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.extesions.componentScope
import com.mironov.weatherapp.presenation.favourite.FavouriteStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultSearchComponent @Inject constructor(
    private val searchStoreFactory: SearchStoreFactory,
    private val openReason: OpenReason,
    componentContext: ComponentContext,
) : SearchComponent, ComponentContext by componentContext {

    private val store = searchStoreFactory.create(openReason)
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect { label ->
                when (label) {
                    SearchStore.Label.ClickBack -> {

                    }

                    is SearchStore.Label.OpenForecast -> {

                    }

                    SearchStore.Label.SavedToFavourite -> {

                    }
                }
            }
        }
    }

    override val model: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickCity(city: City) {
        store.accept(SearchStore.Intent.ClickSearchItem(city))
    }
}