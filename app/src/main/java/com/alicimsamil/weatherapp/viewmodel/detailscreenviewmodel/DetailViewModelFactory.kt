package com.alicimsamil.weatherapp.viewmodel.detailscreenviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicimsamil.weatherapp.data.repository.WeatherRepository

class DetailViewModelFactory constructor(private val repository: WeatherRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailScreenViewModel::class.java)) {
            DetailScreenViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}