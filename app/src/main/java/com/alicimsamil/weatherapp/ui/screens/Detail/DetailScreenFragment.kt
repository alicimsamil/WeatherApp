package com.alicimsamil.weatherapp.ui.screens.Detail

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


class DetailScreenFragment : Fragment() {

    val args: DetailScreenFragmentArgs by navArgs()
    private lateinit var viewModel: DetailScreenViewModel
    private lateinit var celsiusTextView : TextView
    private lateinit var minMaxValueTextView : TextView
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
        celsiusTextView = view.findViewById<TextView>(R.id.celsiusValue)
        minMaxValueTextView = view.findViewById<TextView>(R.id.minMaxValue)
        weatherIconImageView = view.findViewById<ImageView>(R.id.weatherIcon)
        recyclerView = view.findViewById(R.id.detailRecyclerView)
        val linearLayout = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
        recyclerView.layoutManager=linearLayout
        adapter = context?.let { DetailScreenAdapter(it) }!!
        recyclerView.adapter=adapter
        observeWeather()



    }



    fun observeWeather(){


        context?.let {
            val woeid=args.woeid
            viewModel.getWeather(it,woeid)

        }

        viewModel.weatherModel.observe(viewLifecycleOwner, Observer {
            val todayWeather = it.consolidated_weather.get(0)

            celsiusTextView.text=todayWeather.the_temp.toInt().toString()+"°C"
            minMaxValueTextView.text="Min/Maks Sıcaklık: "+todayWeather.min_temp.toInt().toString()+"°C / "+todayWeather.max_temp.toInt().toString()+"°C"

            it.consolidated_weather.forEach {
                println(it.the_temp)

            }
            when(todayWeather.weather_state_abbr){

                "sn" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.sn))
                "sl" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.sl))
                "h" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.h))
                "r" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.t))
                "hr" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.hr))
                "lr" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.lr))
                "s" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.s))
                "hc" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.hc))
                "lc" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.lc))
                "c" -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.c))

                else -> weatherIconImageView.setImageDrawable(resources.getDrawable(R.drawable.c))
            }

            adapter.weather=it.consolidated_weather.subList(1,6)

        })



    }





}