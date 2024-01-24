package com.mironov.weatherapp.ui.extesions

import kotlin.math.roundToInt

fun Float.tempToFormattedString(): String = "${roundToInt()}°С"