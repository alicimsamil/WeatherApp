package com.alicimsamil.weatherapp.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRetrofit {


    companion object {

        val BASE_URL="https://www.metaweather.com"
        var retrofitService : WeatherService? = null

        fun getInstance() : WeatherService{

            if (retrofitService == null){

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

                retrofitService = retrofit.create(WeatherService::class.java)

            }

            return retrofitService!!

        }

    }








}