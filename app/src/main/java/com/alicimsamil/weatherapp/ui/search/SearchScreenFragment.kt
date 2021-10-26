package com.alicimsamil.weatherapp.ui.search


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
import com.alicimsamil.weatherapp.adapter.SearchScreenAdapter
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.databinding.FragmentSearchScreenBinding
import com.alicimsamil.weatherapp.util.internetAlertDialogShow
import com.alicimsamil.weatherapp.viewmodel.searchscreenviewmodel.SearchScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.searchscreenviewmodel.SearchViewModelFactory


class SearchScreenFragment : Fragment() {
    private lateinit var viewModel: SearchScreenViewModel
    private lateinit var city: String
    private lateinit var adapter: SearchScreenAdapter
    private lateinit var binding:FragmentSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchScreenBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(WeatherRepository(WeatherRetrofit()))
        ).get(SearchScreenViewModel::class.java)
        adapter = SearchScreenAdapter()
        binding.searchRecyclerView.adapter=adapter

        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            if (it) binding.searchProgressBar.visibility = View.VISIBLE else binding.searchProgressBar.visibility =
                View.GONE
        })

        binding.searchBtn.setOnClickListener {
            city = binding.searchEditText.text.toString()
            getCities(city)
        }
    }

    fun getCities(cityName: String) {

        viewModel.internetCheckData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                context?.let { it -> internetAlertDialogShow(it) }
            }
        })

        context?.let {
            viewModel.getCityLocation(it, cityName)
        }

        viewModel.cityLocation.observe(viewLifecycleOwner, Observer {
            adapter.locations = it
        })

    }
}