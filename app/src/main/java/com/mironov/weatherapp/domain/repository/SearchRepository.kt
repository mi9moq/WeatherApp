package com.mironov.weatherapp.domain.repository

import com.mironov.weatherapp.domain.entity.City

interface SearchRepository {

    suspend fun searchCity(query: String): List<City>
}