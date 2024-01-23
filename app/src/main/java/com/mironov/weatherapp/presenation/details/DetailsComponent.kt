package com.mironov.weatherapp.presenation.details

import kotlinx.coroutines.flow.StateFlow

interface DetailsComponentDetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickBack()

    fun onClickChangeFavouriteStatus()
}