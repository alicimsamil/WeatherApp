package com.alicimsamil.weatherapp.viewmodel.mainscreenviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.model.LocationsModel
import com.alicimsamil.weatherapp.util.internetCheck
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainScreenViewModel(private val repository: WeatherRepository) : ViewModel() {

    val locations = MutableLiveData<List<LocationsModel>>()
    val errorMessage = MutableLiveData<String>()
    val internetCheckData = MutableLiveData<Boolean>()
    val progressLiveData = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()

    fun getLocations(context: Context, location: String) {
        progressLiveData.value = false
        viewModelScope.launch {

            if (internetCheck(context)) {
                internetCheckData.value = true
                progressLiveData.value = true
                disposable.add(

                    repository.getLocations(location)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<List<LocationsModel>>() {
                            override fun onSuccess(t: List<LocationsModel>) {
                                locations.value = t
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