package com.alicimsamil.weatherapp.viewmodel.SplashScreenViewModel

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.weatherapp.util.internetCheck
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel(),LocationListener {

    private lateinit var locationManager: LocationManager
    val internetStatus = MutableLiveData<Boolean>()
    val location = MutableLiveData<Location>()

    fun internetStatusCheck(context:Context){

        viewModelScope.launch {

            internetStatus.value=internetCheck(context)

        }

    }


    fun getLocation(context: Context){

        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        when {
            ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,5f,this)

            }
            else -> {
                requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
            }
        }


    }

    override fun onLocationChanged(p0: Location) {
        location.value=p0
    }


}