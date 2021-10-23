package com.alicimsamil.weatherapp.ui.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.viewmodel.MainFragmentViewModel
import com.alicimsamil.weatherapp.viewmodel.MyViewModelFactory
import com.alicimsamil.weatherapp.viewmodel.SplashScreenViewModel

class MainFragment : Fragment() {
    private lateinit var viewModel: MainFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,MyViewModelFactory(WeatherRepository(WeatherRetrofit()))).get(MainFragmentViewModel::class.java)

        observeLocations()



    }

    fun observeLocations(){

        context?.let {
            viewModel.getLocations(it,"38.3513,38.3280")
        }

        viewModel.locations.observe(viewLifecycleOwner, Observer {
            it.forEach {
                println(it.title)
            }
        })

    }



}