package com.mironov.weatherapp.presenation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.extesions.componentScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultFavouriteComponent @Inject constructor(
    private val favouriteStoreFactory: FavouriteStoreFactory,
    private val componentContext: ComponentContext,
) : FavouriteComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { favouriteStoreFactory.create() }
    private val scope = componentScope()

    init {

        scope.launch {
            store.labels.collect { label ->
                when (label) {
                    is FavouriteStore.Label.CityItemClicked -> {

                    }

                    FavouriteStore.Label.ClickSearch -> {

                    }

                    FavouriteStore.Label.ClickToFavourite -> {

                    }
                }
            }
        }
    }

    override val model: StateFlow<FavouriteStore.State> = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickToFavourite)
    }

    override fun onItemCityClick(city: City) {
        store.accept(FavouriteStore.Intent.CityItemClicked(city))
    }
}