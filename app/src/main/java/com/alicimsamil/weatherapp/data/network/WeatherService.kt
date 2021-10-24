package com.alicimsamil.weatherapp.data.network

import com.alicimsamil.weatherapp.model.CityLocationModel
import com.alicimsamil.weatherapp.model.LocationsModel
import com.alicimsamil.weatherapp.model.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("/api/location/search")
    fun getLocations(@Query("lattlong") lattlong : String) : Single<List<LocationsModel>>

    @GET("/api/location/{woeid}")
    fun getWeatherStatus(@Path("woeid") woeid : String) : Single<WeatherModel>

    @GET("/api/location/search")
    fun getCityLocation(@Query("query") city : String) : Single<List<CityLocationModel>>

}