package com.alicimsamil.weatherapp.ui.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.adapter.MainScreenAdapter
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.viewmodel.MainFragmentViewModel
import com.alicimsamil.weatherapp.viewmodel.MyViewModelFactory

class MainFragment : Fragment() {
    val args:MainFragmentArgs by navArgs()
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: MainScreenAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,MyViewModelFactory(WeatherRepository(WeatherRetrofit()))).get(MainFragmentViewModel::class.java)
        recyclerView = view.findViewById(R.id.mainRecyclerView)
        val linearLayout = LinearLayoutManager(context)
        recyclerView.layoutManager=linearLayout
        adapter = MainScreenAdapter()
        recyclerView.adapter=adapter
        observeLocations()

    }

    private fun observeLocations(){

        context?.let {
            viewModel.getLocations(it,args.latlng)
        }

        viewModel.locations.observe(viewLifecycleOwner, Observer {
            adapter.locations=it
        })

    }



}