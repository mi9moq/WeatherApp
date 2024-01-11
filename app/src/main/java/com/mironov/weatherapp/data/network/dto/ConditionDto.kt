package com.mironov.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    val text: String,
    @SerializedName("icon")
    val iconUrl: String
)
