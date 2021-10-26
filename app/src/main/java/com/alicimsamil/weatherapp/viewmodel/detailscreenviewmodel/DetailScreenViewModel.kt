package com.alicimsamil.weatherapp.viewmodel.detailscreenviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.model.WeatherModel
import com.alicimsamil.weatherapp.util.internetCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: WeatherRepository) : ViewModel() {

    val weatherModel = MutableLiveData<WeatherModel>()
    val errorMessage = MutableLiveData<String>()
    val internetCheckData = MutableLiveData<Boolean>()
    val progressLiveData = MutableLiveData<Boolean>()
    private val disposable= CompositeDisposable()

    fun getWeather(context: Context,woeid:String){
        progressLiveData.value=false
        viewModelScope.launch {

            if (internetCheck(context)) {
                progressLiveData.value=true
                internetCheckData.value=true
                disposable.add(
                    repository.getWeatherStatus(woeid)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {
                            override fun onSuccess(t: WeatherModel) {
                                weatherModel.value=t
                                progressLiveData.value=false

                            }
                            override fun onError(e: Throwable) {
                                errorMessage.value=e.message
                            }

                        })
                )

            } else{
                internetCheckData.value=false
                progressLiveData.value=false
            }

        }

    }
}