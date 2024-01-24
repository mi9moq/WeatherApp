package com.mironov.weatherapp.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.mironov.weatherapp.presenation.root.RootComponent
import com.mironov.weatherapp.ui.details.DetailsContent
import com.mironov.weatherapp.ui.favourite.FavouriteContent
import com.mironov.weatherapp.ui.search.SearchContent
import com.mironov.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun RootContent(component: RootComponent) {

    WeatherAppTheme {
        Children(stack = component.stack) {
            when (val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }

                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }

                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}