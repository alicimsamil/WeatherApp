package com.alicimsamil.weatherapp.viewmodel.searchscreenviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alicimsamil.weatherapp.data.repository.WeatherRepository

class SearchViewModelFactory constructor(private val repository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchScreenViewModel::class.java)) {
            SearchScreenViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}