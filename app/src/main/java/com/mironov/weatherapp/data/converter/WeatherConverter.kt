package com.mironov.weatherapp.data.converter

import com.mironov.weatherapp.data.network.dto.CurrentWeatherResponse
import com.mironov.weatherapp.data.network.dto.ForecastWeatherResponse
import com.mironov.weatherapp.data.network.dto.WeatherDto
import com.mironov.weatherapp.domain.entity.Forecast
import com.mironov.weatherapp.domain.entity.ForecastWeather
import com.mironov.weatherapp.domain.entity.Weather
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class WeatherConverter @Inject constructor() {

    private fun weatherDtoToEntity(from: WeatherDto): Weather = Weather(
        temp = from.temp,
        date = from.lastUpdate.toCalendar(),
        condition = from.condition.text,
        conditionUrl = from.condition.iconUrl.correctImageUrl(),
    )

    fun weatherResponseToEntity(from: CurrentWeatherResponse): Weather =
        weatherDtoToEntity(from.currentWeather)

    fun forecastResponseToEntity(from: ForecastWeatherResponse): Forecast = Forecast(
        currentWeather = from.current.let(::weatherDtoToEntity),
        upcoming = from.forecast.forecastWeather.drop(1).map { dayDto ->
            val dayWeatherDto = dayDto.forecastWeather
            ForecastWeather(
                minTemp = dayWeatherDto.minTemp,
                maxTemp = dayWeatherDto.maxTemp,
                date = dayDto.date.toCalendar(),
                condition = dayWeatherDto.condition.text,
                conditionUrl = dayWeatherDto.condition.iconUrl.correctImageUrl(),
            )
        }
    )
}

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar * 1000)
}

private fun String.correctImageUrl() = "$PROTOCOL$this".replace(
    oldValue = OLD_IMAGE_QUALITY,
    newValue = NEW_IMAGE_QUALITY
)

private const val OLD_IMAGE_QUALITY = "64x64"
private const val NEW_IMAGE_QUALITY = "128x128"
private const val PROTOCOL = "https:"