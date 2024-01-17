package com.mironov.weatherapp.data.datasource

import com.mironov.weatherapp.data.converter.CityConverter
import com.mironov.weatherapp.data.local.db.FavouriteCitiesDao
import com.mironov.weatherapp.domain.entity.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteLocalDataSourceImpl @Inject constructor(
    private val dao: FavouriteCitiesDao,
    private val converter: CityConverter,
) : FavouriteLocalDataSource {
    override fun getAll(): Flow<List<City>> =
        dao.getAll().map { it.map(converter::dbToEntity) }

    override suspend fun add(city: City) {
        dao.add(converter.entityToDb(city))
    }

    override suspend fun remove(id: Int) {
        dao.remove(id)
    }

    override fun isFavorite(id: Int): Flow<Boolean> =
        dao.isFavourite(id)
}