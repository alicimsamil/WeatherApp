package com.alicimsamil.weatherapp.viewmodel.searchscreenviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.model.CityLocationModel
import com.alicimsamil.weatherapp.util.internetCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SearchScreenViewModel(private val repository: WeatherRepository) : ViewModel() {
    val cityLocation = MutableLiveData<List<CityLocationModel>>()
    val errorMessage = MutableLiveData<String>()
    val progressLiveData = MutableLiveData<Boolean>()
    val internetCheckData = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    fun getCityLocation(context: Context, city: String) {
        progressLiveData.value = false
        viewModelScope.launch {

            if (internetCheck(context)) {
                internetCheckData.value = true
                progressLiveData.value = true
                disposable.add(

                    repository.getCityLocation(city)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object :
                            DisposableSingleObserver<List<CityLocationModel>>() {
                            override fun onSuccess(t: List<CityLocationModel>) {
                                cityLocation.value = t
                                progressLiveData.value = false
                            }

                            override fun onError(e: Throwable) {
                                errorMessage.value = e.message
                            }

                        })
                )

            } else {
                internetCheckData.value = false
                progressLiveData.value = false
            }
        }
    }
}