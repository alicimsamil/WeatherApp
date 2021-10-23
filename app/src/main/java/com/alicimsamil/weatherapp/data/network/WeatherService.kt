package com.alicimsamil.weatherapp.data.network

import com.alicimsamil.weatherapp.model.LocationsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {


    @GET("/api/location/search")
    fun getLocations(@Query("lattlong") lattlong : String): Single<ArrayList<LocationsModel>>


}