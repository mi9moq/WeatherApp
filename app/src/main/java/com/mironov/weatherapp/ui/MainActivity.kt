package com.mironov.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.mironov.weatherapp.WeatherApp
import com.mironov.weatherapp.presenation.root.DefaultRootComponent
import com.mironov.weatherapp.ui.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WeatherApp).component.inject(this)
        super.onCreate(savedInstanceState)

        val rootComponent = rootComponentFactory.create(defaultComponentContext())

        setContent {
            RootContent(component = rootComponent)
        }
    }
}