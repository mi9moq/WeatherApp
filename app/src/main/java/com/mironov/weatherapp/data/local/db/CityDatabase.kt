package com.mironov.weatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mironov.weatherapp.data.local.model.CityDbModel

@Database(entities = [CityDbModel::class], version = 1, exportSchema = false)
abstract class CityDatabase : RoomDatabase() {

    abstract fun citiesDao(): FavouriteCitiesDao
}