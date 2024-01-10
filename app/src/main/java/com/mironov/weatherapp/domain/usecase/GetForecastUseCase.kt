package com.mironov.weatherapp.domain.usecase

import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend operator fun invoke(cityId: Int): Forecast = repository.getForecast(cityId)
}