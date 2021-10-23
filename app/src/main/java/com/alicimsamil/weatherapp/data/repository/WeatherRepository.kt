package com.alicimsamil.weatherapp.data.repository

import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.network.WeatherService

class WeatherRepository(private val weatherRetrofit: WeatherRetrofit) {

    fun getLocations(location : String) = weatherRetrofit.apiService().getLocations(location)
    fun getWeatherStatus(woeid : String) = weatherRetrofit.apiService().getWeatherStatus(woeid)


}