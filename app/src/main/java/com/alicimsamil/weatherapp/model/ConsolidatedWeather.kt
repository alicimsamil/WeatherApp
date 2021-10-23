package com.alicimsamil.weatherapp.model

data class ConsolidatedWeather(
    val air_pressure: Double,
    val applicable_date: String,
    val created: String,
    val humidity: Int,
    val id: Long,
    val max_temp: Double,
    val min_temp: Double,
    val predictability: Int,
    val the_temp: Double,
    val visibility: Double,
    val weather_state_abbr: String,
    val weather_state_name: String,
    val wind_direction: Double,
    val wind_direction_compass: String,
    val wind_speed: Double
)