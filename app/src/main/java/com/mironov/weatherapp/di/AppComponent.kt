package com.mironov.weatherapp.di

import dagger.Component

@AppScope
@Component(modules = [
    DataModule::class
])
interface AppComponent {
}