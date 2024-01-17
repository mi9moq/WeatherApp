package com.mironov.weatherapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        DataBaseModule::class,
        DispatcherModule::class,
        FavouriteModule::class,
        SearchModule::class,
    ]
)
interface AppComponent {


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}