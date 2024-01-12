package com.mironov.weatherapp.data.converter

import com.mironov.weatherapp.data.network.dto.CityDto
import com.mironov.weatherapp.domain.entity.City
import javax.inject.Inject

class CityConverter @Inject constructor() {

    fun dtoToEntity(from: CityDto): City = City(
        id = from.id,
        name = from.name,
        country = from.country
    )
}