package com.alicimsamil.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.model.LocationsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainFragmentViewModel(private val repository: WeatherRepository) : ViewModel() {

    val locations = MutableLiveData<ArrayList<LocationsModel>>()
    private val disposable=CompositeDisposable()


    fun getLocations(location:String){

        disposable.add(

            repository.getLocations(location)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArrayList<LocationsModel>>(){
                    override fun onSuccess(t: ArrayList<LocationsModel>) {
                        locations.value=t
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }


                })


        )


    }


}