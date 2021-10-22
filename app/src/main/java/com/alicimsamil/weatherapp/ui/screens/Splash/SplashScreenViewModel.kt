package com.alicimsamil.weatherapp.ui.screens.Splash

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicimsamil.weatherapp.util.internetCheck
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    val internetStatus = MutableLiveData<Boolean>()

    fun internetStatusCheck(context:Context){

        viewModelScope.launch {

            internetStatus.value=internetCheck(context)

        }

    }


}