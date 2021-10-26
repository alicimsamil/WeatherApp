package com.alicimsamil.weatherapp.ui.screens.main

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
import com.alicimsamil.weatherapp.databinding.FragmentMainBinding
import com.alicimsamil.weatherapp.util.internetAlertDialogShow
import com.alicimsamil.weatherapp.viewmodel.mainscreenviewmodel.MainScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.mainscreenviewmodel.MainViewModelFactory

class MainScreenFragment : Fragment() {
    val args: MainScreenFragmentArgs by navArgs()
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var adapter: MainScreenAdapter
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(WeatherRepository(WeatherRetrofit()))
        ).get(MainScreenViewModel::class.java)
        adapter = MainScreenAdapter()
        binding.mainRecyclerView.adapter = adapter
        observeLocations()

        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            if (it) binding.mainProgressBar.visibility =
                View.VISIBLE else binding.mainProgressBar.visibility = View.GONE
        })

        binding.searchMainBtn.setOnClickListener {
            val action = MainScreenFragmentDirections.actionMainFragmentToSearchScreenFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeLocations() {
        context?.let {
            viewModel.getLocations(it, args.latlng)
        }

        viewModel.internetCheckData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                context?.let { it -> internetAlertDialogShow(it) }
            } else {
                viewModel.locations.observe(viewLifecycleOwner, Observer {
                    adapter.locations = it
                })
            }
        })
    }
}