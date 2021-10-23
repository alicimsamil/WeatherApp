package com.alicimsamil.weatherapp.viewmodel

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

class MainFragmentViewModel(private val repository: WeatherRepository) : ViewModel() {

    val locations = MutableLiveData<ArrayList<LocationsModel>>()
    private val disposable=CompositeDisposable()

    fun getLocations(context:Context, location:String){

    viewModelScope.launch {

        if (internetCheck(context)) {

            disposable.add(

                repository.getLocations(location)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ArrayList<LocationsModel>>() {
                        override fun onSuccess(t: ArrayList<LocationsModel>) {
                            locations.value = t
                        }

                        override fun onError(e: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
            )

        }

    }

    }


}