package com.alicimsamil.weatherapp.model

data class CityLocationModel(
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)