package com.alicimsamil.weatherapp.ui.screens.Splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alicimsamil.weatherapp.R

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
        context?.let {
            viewModel.internetStatusCheck(it)
        }

        viewModel.internetStatus.observe(viewLifecycleOwner, Observer {
            if (it){
                Toast.makeText(context,"İnternet bağlantısı var.",Toast.LENGTH_LONG).show()

            }
            else if(!it){

                Toast.makeText(context,"İnternet bağlantısı yok! Bağlantınızı kontrol ediniz.",Toast.LENGTH_LONG).show()

            }


        })








    }


}