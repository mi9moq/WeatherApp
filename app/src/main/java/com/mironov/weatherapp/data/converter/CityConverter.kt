package com.mironov.weatherapp.data.converter

import com.mironov.weatherapp.data.local.model.CityDbModel
import com.mironov.weatherapp.data.network.dto.CityDto
import com.mironov.weatherapp.domain.entity.City
import javax.inject.Inject

class CityConverter @Inject constructor() {

    fun dtoToEntity(from: CityDto): City = City(
        id = from.id,
        name = from.name,
        country = from.country
    )

    fun dbToEntity(from: CityDbModel): City = City(
        id = from.id,
        name = from.name,
        country = from.country,
    )

    fun entityToDb(from: City): CityDbModel = CityDbModel(
        id = from.id,
        name = from.name,
        country = from.country,
    )
}