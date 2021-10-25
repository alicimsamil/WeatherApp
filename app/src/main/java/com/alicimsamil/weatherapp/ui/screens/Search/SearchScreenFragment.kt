package com.alicimsamil.weatherapp.ui.screens.Search

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.adapter.MainScreenAdapter
import com.alicimsamil.weatherapp.adapter.SearchScreenAdapter
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.viewmodel.MainScreenViewModel.MainScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.MainScreenViewModel.MainViewModelFactory
import com.alicimsamil.weatherapp.viewmodel.SearchScreenViewModel.SearchScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.SearchScreenViewModel.SearchViewModelFactory
import kotlin.system.exitProcess


class SearchScreenFragment : Fragment() {
    private lateinit var viewModel: SearchScreenViewModel
    private lateinit var searchEditText : EditText
    private lateinit var searchButton : ImageButton
    private lateinit var city : String
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchProgressBar: ProgressBar
    private lateinit var adapter: SearchScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_screen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, SearchViewModelFactory(WeatherRepository(WeatherRetrofit()))).get(SearchScreenViewModel::class.java)
        searchButton = view.findViewById(R.id.searchBtn)
        searchEditText = view.findViewById(R.id.searchEditText)
        searchProgressBar=view.findViewById(R.id.searchProgressBar)
        recyclerView = view.findViewById(R.id.searchRecyclerView)
        val linearLayout = LinearLayoutManager(context)
        recyclerView.layoutManager=linearLayout
        adapter = SearchScreenAdapter()
        recyclerView.adapter=adapter


        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            if (it){
                searchProgressBar.visibility=View.VISIBLE
            }
            else{
                searchProgressBar.visibility=View.GONE
            }
        })


        searchButton.setOnClickListener(View.OnClickListener {
            city=searchEditText.text.toString()
            getCities(city)
        })


    }


    fun getCities(cityName:String){

        viewModel.internetCheckData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle("İnternet Bağlantısı")
                alertDialog.setMessage("İnternete bağlanılamadı.")
                alertDialog.setPositiveButton("Çıkış Yap") { dialog, which ->
                    exitProcess(-1)
                }
                alertDialog.show()
            }

        })

        context?.let {
            viewModel.getCityLocation(it,cityName)
        }


        viewModel.cityLocation.observe(viewLifecycleOwner, Observer {
            adapter.locations=it
        })



    }




}