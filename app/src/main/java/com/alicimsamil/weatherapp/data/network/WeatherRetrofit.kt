package com.alicimsamil.weatherapp.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRetrofit {

    val BASE_URL="https://www.metaweather.com"
    fun apiService(): WeatherService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()


        return retrofit.create<WeatherService>(WeatherService::class.java)

    }




}