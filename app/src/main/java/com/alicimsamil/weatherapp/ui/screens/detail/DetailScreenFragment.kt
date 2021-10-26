package com.alicimsamil.weatherapp.ui.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.adapter.DetailScreenAdapter
import com.alicimsamil.weatherapp.data.network.WeatherRetrofit
import com.alicimsamil.weatherapp.data.repository.WeatherRepository
import com.alicimsamil.weatherapp.databinding.FragmentDetailScreenBinding
import com.alicimsamil.weatherapp.util.internetAlertDialogShow
import com.alicimsamil.weatherapp.util.weatherIconPicker
import com.alicimsamil.weatherapp.viewmodel.detailscreenviewmodel.DetailScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.detailscreenviewmodel.DetailViewModelFactory


class DetailScreenFragment : Fragment() {

    val args: DetailScreenFragmentArgs by navArgs()
    private lateinit var viewModel: DetailScreenViewModel
    private lateinit var adapter: DetailScreenAdapter
    private lateinit var binding: FragmentDetailScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(WeatherRepository(WeatherRetrofit()))
        ).get(DetailScreenViewModel::class.java)
        observeWeather()

        viewModel.progressLiveData.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.detailProgressBar.visibility = View.VISIBLE
            } else {
                binding.detailProgressBar.visibility = View.GONE
            }
        })

        adapter = context?.let { DetailScreenAdapter(it) }!!
        binding.detailRecyclerView.adapter = adapter

    }

    private fun observeWeather() {
        context?.let {
            val woeid = args.woeid
            viewModel.getWeather(it, woeid)
        }

        viewModel.internetCheckData.observe(viewLifecycleOwner, Observer {
            if (!it) {
                context?.let { it -> internetAlertDialogShow(it) }
            }

        })

        viewModel.weatherModel.observe(viewLifecycleOwner, Observer {
            val todayWeather = it.consolidated_weather.get(0)
            binding.apply {
                weatherCity.text = args.city
                weeklyWeatherStatus.text = getString(R.string.weeklyWeather)
                humidityImageView.setImageResource(R.drawable.humidity)
                locationWeatherIcon.setImageResource(R.drawable.location)
                celsiusValue.text = "${todayWeather.the_temp.toInt()}°C"
                minMaxValue.text =
                    "Min/Maks Sıcaklık: ${todayWeather.min_temp.toInt()}°C / ${todayWeather.max_temp.toInt()}°C"
                humidityTextView.text = "Nem Oranı: ${todayWeather.humidity}"
                weatherIcon.setImageDrawable(context?.let { it ->
                    weatherIconPicker(
                        todayWeather.weather_state_abbr,
                        it
                    )
                })
            }
            adapter.weather = it.consolidated_weather.subList(1, 6)
        })

    }
}