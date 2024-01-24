package com.mironov.weatherapp.presenation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.mironov.weatherapp.presenation.details.DetailsComponent
import com.mironov.weatherapp.presenation.favourite.FavouriteComponent
import com.mironov.weatherapp.presenation.search.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child

        data class Details(val component: DetailsComponent) : Child

        data class Search(val component: SearchComponent) : Child
    }
}