package com.mironov.weatherapp.di

import dagger.Component

@AppScope
@Component(modules = [
    NetworkModule::class
])
interface AppComponent {
}