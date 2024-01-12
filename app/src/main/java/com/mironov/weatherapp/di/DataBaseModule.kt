package com.mironov.weatherapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mironov.weatherapp.data.local.db.CityDatabase
import com.mironov.weatherapp.data.local.db.FavouriteCitiesDao
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    companion object {

        private const val DB_NAME = "cities.db"
    }

    @AppScope
    @Provides
    fun provideCitiesDatabase(application: Application): CityDatabase = Room.databaseBuilder(
        application,
        CityDatabase::class.java,
        DB_NAME
    ).build()

    @AppScope
    @Provides
    fun provideFavouriteCitiesDao(database: CityDatabase): FavouriteCitiesDao =
        database.citiesDao()
}