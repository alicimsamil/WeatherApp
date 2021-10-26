package com.alicimsamil.weatherapp.ui.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.adapter.MainScreenAdapter
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.util.internetAlertDialogShow
import com.alicimsamil.weatherapp.viewmodel.MainScreenViewModel.MainScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.MainScreenViewModel.MainViewModelFactory
import kotlin.system.exitProcess

class MainScreenFragment : Fragment() {
    val args:MainScreenFragmentArgs by navArgs()
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: MainScreenAdapter
    private lateinit var searchMainButton: ImageButton
    private lateinit var mainProgressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,
            MainViewModelFactory(WeatherRepository(WeatherRetrofit()))
        ).get(MainScreenViewModel::class.java)
        searchMainButton = view.findViewById(R.id.searchMainBtn)
        recyclerView = view.findViewById(R.id.mainRecyclerView)
        mainProgressBar = view.findViewById(R.id.mainProgressBar)
        val linearLayout = LinearLayoutManager(context)
        recyclerView.layoutManager=linearLayout
        adapter = MainScreenAdapter()
        recyclerView.adapter=adapter
        observeLocations()

        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            if (it){
                mainProgressBar.visibility=View.VISIBLE
            }
            else{
                mainProgressBar.visibility=View.GONE
            }
        })

        searchMainButton.setOnClickListener(View.OnClickListener {

            val action = MainScreenFragmentDirections.actionMainFragmentToSearchScreenFragment()
            Navigation.findNavController(it).navigate(action)

        })

    }

    private fun observeLocations(){
        context?.let {
            viewModel.getLocations(it,args.latlng)
        }

        viewModel.internetCheckData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                context?.let { it -> internetAlertDialogShow(it) }
            } else{
                viewModel.locations.observe(viewLifecycleOwner, Observer {
                    adapter.locations=it
                })
            }

        })


    }

}