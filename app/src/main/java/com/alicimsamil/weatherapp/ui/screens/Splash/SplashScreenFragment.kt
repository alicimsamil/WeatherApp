package com.alicimsamil.weatherapp.ui.screens.Splash

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.viewmodel.SplashScreenViewModel.SplashScreenViewModel
import kotlinx.coroutines.*

class SplashScreenFragment : Fragment() {
    private val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            observeInternet()
            delay(3000)
            observeLocation()
        }

    }



    fun observeInternet(){
        context?.let {
            viewModel.internetStatusCheck(it)
        }
        viewModel.internetStatus.observe(viewLifecycleOwner, Observer {
            if (it){

            }
            else if(!it){
                Toast.makeText(context,"İnternet bağlantısı yok! Bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG).show()
            }

        })

    }



    fun observeLocation(){
        context?.let {
            viewModel.getLocation(it)
        }
        viewModel.location.observe(viewLifecycleOwner, Observer {
            println(it.latitude)
            val lltlng = it.latitude.toString()+","+it.longitude.toString()
            val action = SplashScreenFragmentDirections.actionSplashScreenToMainFragment(lltlng)
            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        })
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 2) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


}