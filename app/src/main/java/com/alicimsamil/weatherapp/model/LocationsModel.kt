package com.alicimsamil.weatherapp.model

data class LocationsModel(
    val distance: Int,
    val latt_long: String,
    val location_type: String,
    val title: String,
    val woeid: Int
)