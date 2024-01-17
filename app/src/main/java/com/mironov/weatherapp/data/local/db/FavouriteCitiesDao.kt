package com.mironov.weatherapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mironov.weatherapp.data.local.model.CityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCitiesDao {

    @Query("SELECT * FROM favourite_cities")
    fun getAll(): Flow<List<CityDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(city: CityDbModel)

    @Query("DELETE FROM favourite_cities WHERE id=:id")
    suspend fun remove(id: Int)

    @Query("SELECT EXISTS (SELECT * FROM favourite_cities WHERE id=:id LIMIT 1)")
    fun isFavourite(id: Int): Flow<Boolean>
}