package com.mironov.weatherapp

import android.app.Application
import com.mironov.weatherapp.di.AppComponent
import com.mironov.weatherapp.di.DaggerAppComponent

class WeatherApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }
}