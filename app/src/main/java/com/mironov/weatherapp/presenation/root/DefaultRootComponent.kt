package com.mironov.weatherapp.presenation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.mironov.weatherapp.domain.entity.City
import com.mironov.weatherapp.presenation.details.DefaultDetailsComponent
import com.mironov.weatherapp.presenation.favourite.DefaultFavouriteComponent
import com.mironov.weatherapp.presenation.search.DefaultSearchComponent
import com.mironov.weatherapp.presenation.search.OpenReason
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is Config.Details -> {
            applyDetails(componentContext, config.city)
        }

        Config.Favourite -> {
            applyFavourite(componentContext)
        }

        is Config.Search -> {
            applySearch(componentContext, config.openReason)
        }
    }

    private fun applyDetails(
        componentContext: ComponentContext,
        city: City
    ): RootComponent.Child.Details {
        val component = detailsComponentFactory.create(
            city = city,
            onBackClicked = {
                navigation.pop()
            },
            componentContext = componentContext
        )
        return RootComponent.Child.Details(component)
    }

    private fun applyFavourite(
        componentContext: ComponentContext,
    ): RootComponent.Child.Favourite {
        val component = favouriteComponentFactory.create(
            onCityItemClicked = {
                navigation.push(Config.Details(it))
            },
            onAddFavouriteClicked = {
                navigation.push(Config.Search(OpenReason.AddToFavourite))
            },
            onSearchClicked = {
                navigation.push(Config.Search(OpenReason.RegularSearch))
            },
            componentContext = componentContext,
        )
        return RootComponent.Child.Favourite(component)
    }

    private fun applySearch(
        componentContext: ComponentContext,
        openReason: OpenReason
    ): RootComponent.Child.Search {
        val component = searchComponentFactory.create(
            openReason = openReason,
            onBackClicked = {
                navigation.pop()
            },
            onCitySavedToFavourite = {
                navigation.pop()
            },
            onForecastForCityRequested = {
                navigation.push(Config.Details(it))
            },
            componentContext = componentContext
        )
        return RootComponent.Child.Search(component)
    }

    sealed interface Config : Parcelable {

        @Parcelize
        data object Favourite : Config

        @Parcelize
        data class Search(val openReason: OpenReason) : Config

        @Parcelize
        data class Details(val city: City) : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}