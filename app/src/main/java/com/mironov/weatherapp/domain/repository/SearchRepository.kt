package com.mironov.weatherapp.domain.repository

import com.mironov.weatherapp.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>
}