package com.mironov.weatherapp.data.network

import com.mironov.weatherapp.data.network.dto.CityDto
import com.mironov.weatherapp.data.network.dto.CurrentWeatherResponse
import com.mironov.weatherapp.data.network.dto.ForecastWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("search.json")
    suspend fun loadCurrentWeather(
        @Query("q") query: String,
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun loadForecastWeather(
        @Query("q") query: String,
        @Query("days") days: Int = 4,
    ): ForecastWeatherResponse

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>
}