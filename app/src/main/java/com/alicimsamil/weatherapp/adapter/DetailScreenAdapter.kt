package com.alicimsamil.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.model.ConsolidatedWeather
import com.alicimsamil.weatherapp.util.weatherIconPicker

class DetailScreenAdapter(val context: Context) : RecyclerView.Adapter<DetailViewHolder>() {

    var weather = listOf<ConsolidatedWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weather_recycler_shape,parent,false)
        return DetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val weatherIconImageView=holder.itemView.findViewById<ImageView>(R.id.dailyWeatherIcon)
        val weatherCelcius=holder.itemView.findViewById<TextView>(R.id.dailyWeatherCelcius)
        weatherCelcius.text="${weather.get(position).the_temp.toInt()}°C"
        weatherIconImageView.setImageDrawable(weatherIconPicker(weather.get(position).weather_state_abbr,context))

    }

    override fun getItemCount(): Int {
        return weather.size
    }

}

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}