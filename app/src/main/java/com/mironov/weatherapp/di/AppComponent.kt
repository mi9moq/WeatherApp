package com.mironov.weatherapp.di

import android.app.Application
import com.mironov.weatherapp.ui.MainActivity
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
        WeatherModule::class,
        MviModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}