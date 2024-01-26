package com.mironov.weatherapp.ui.extesions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.roundToInt

fun Float.tempToFormattedString(): String = "${roundToInt()}°С"

private const val datePattern = "EEEE | d MMM y"
private const val dayOfWeekPattern = "EEE"
fun Calendar.formattedDate(): String {
    val format = SimpleDateFormat(datePattern, Locale.getDefault())
    return format.format(time)
}

fun Calendar.formattedDayOfWeek(): String {
    val format = SimpleDateFormat(dayOfWeekPattern, Locale.getDefault())
    return format.format(time)
}

fun formattedUpcomingWeather(minTemp: Float, maxTemp: Float): String =
    "${minTemp.roundToInt()} / ${maxTemp.roundToInt()}°С"