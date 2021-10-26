package com.alicimsamil.weatherapp.ui.splash

import android.Manifest
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
import com.alicimsamil.weatherapp.util.internetAlertDialogShow
import com.alicimsamil.weatherapp.viewmodel.splashscreenviewmodel.SplashScreenViewModel
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

        // We use coroutine to prevent the interface from being blocked during the operations.
        CoroutineScope(Dispatchers.Main).launch {

            //Request GPS permission
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
            delay(3000)
        }

    }


    fun observeInternet() {
        context?.let {
            viewModel.internetStatusCheck(it)
        }
        viewModel.internetStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                observeLocation()
            } else if (!it) {
                context?.let { it -> internetAlertDialogShow(it) }
            }

        })

    }

    private fun observeLocation() {
        context?.let {
            viewModel.getLocation(it)
        }
        viewModel.location.observe(viewLifecycleOwner, Observer {
            val lltlng = it.latitude.toString() + "," + it.longitude.toString()
            val action = SplashScreenFragmentDirections.actionSplashScreenToMainFragment(
                lltlng
            )
            view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 2) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                observeInternet()
            } else {
                Toast.makeText(context, getString(R.string.permissionDenied), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }


}