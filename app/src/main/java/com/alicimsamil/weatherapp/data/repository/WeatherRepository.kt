package com.alicimsamil.weatherapp.data.repository

import com.alicimsamil.weatherapp.data.network.WeatherService

class WeatherRepository(private val weatherService: WeatherService) {

    fun getLocations(location:String) = weatherService.getLocations(location)


}