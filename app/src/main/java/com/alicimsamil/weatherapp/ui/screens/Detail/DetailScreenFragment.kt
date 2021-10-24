package com.alicimsamil. weatherapp.ui.screens.Detail

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import com.alicimsamil.weatherapp.viewmodel.DetailScreenViewModel.DetailScreenViewModel
import com.alicimsamil.weatherapp.viewmodel.DetailScreenViewModel.DetailViewModelFactory
import kotlin.system.exitProcess


class DetailScreenFragment : Fragment() {

    val args: DetailScreenFragmentArgs by navArgs()
    private lateinit var viewModel : DetailScreenViewModel
    private lateinit var celsiusTextView : TextView
    private lateinit var minMaxValueTextView : TextView
    private lateinit var cityTextView : TextView
    private lateinit var humidityTextView : TextView
    private lateinit var humidityImageView: ImageView
    private lateinit var locationWeatherIcon: ImageView
    private lateinit var weeklyWeatherStatus : TextView
    private lateinit var weatherIconImageView : ImageView
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter: DetailScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, DetailViewModelFactory(WeatherRepository(WeatherRetrofit()))).get(DetailScreenViewModel::class.java)
        observeWeather()
        celsiusTextView = view.findViewById(R.id.celsiusValue)
        minMaxValueTextView = view.findViewById(R.id.minMaxValue)
        cityTextView = view.findViewById(R.id.weatherCity)
        humidityImageView = view.findViewById(R.id.humidityImageView)
        humidityTextView = view.findViewById(R.id.humidityTextView)
        locationWeatherIcon = view.findViewById(R.id.locationWeatherIcon)
        weeklyWeatherStatus = view.findViewById(R.id.weeklyWeatherStatus)
        weatherIconImageView = view.findViewById(R.id.weatherIcon)
        recyclerView = view.findViewById(R.id.detailRecyclerView)

        val linearLayout = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        recyclerView.layoutManager =linearLayout
        adapter = context?.let { DetailScreenAdapter(it) }!!
        recyclerView.adapter=adapter

    }



    fun observeWeather(){


        context?.let {
            val woeid=args.woeid
            viewModel.getWeather(it,woeid)

        }

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

        viewModel.weatherModel.observe(viewLifecycleOwner, Observer {
            val todayWeather = it.consolidated_weather.get(0)
            cityTextView.text=args.city
            weeklyWeatherStatus.text="Haftalık Hava Durumu"
            humidityImageView.setImageDrawable(resources.getDrawable(R.drawable.humidity,context?.theme))
            locationWeatherIcon.setImageDrawable(resources.getDrawable(R.drawable.location,context?.theme))
            celsiusTextView.text  ="${todayWeather.the_temp.toInt()}°C"
            minMaxValueTextView.text="Min/Maks Sıcaklık: ${todayWeather.min_temp.toInt()}°C / ${todayWeather.max_temp.toInt()}°C"
            humidityTextView.text="Nem Oranı: ${todayWeather.humidity}"

            when(todayWeather.weather_state_abbr){

                "sn" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.sn,context?.theme))
                "sl" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.sl,context?.theme))
                "h" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.h,context?.theme))
                "r" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.t,context?.theme))
                "hr" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.hr,context?.theme))
                "lr" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.lr,context?.theme))
                "s" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.s,context?.theme))
                "hc" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.hc,context?.theme))
                "lc" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.lc,context?.theme))
                "c" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.c,context?.theme))

                else -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.c,context?.theme))
            }

            adapter.weather=it.consolidated_weather.subList(1,6)

        })



    }





}